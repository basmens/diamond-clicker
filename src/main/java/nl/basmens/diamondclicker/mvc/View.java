package nl.basmens.diamondclicker.mvc;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import nl.benmens.processing.SharedPApplet;

public class View {
  private View parentView = null;
  private ArrayList<View> childViews = new ArrayList<View>();

  public Rectangle2D.Float frameRect; // View dimension in Parent coordinates
  public Rectangle2D.Float boundsRect; // View dimension

  public boolean shouldClip = false;
  public boolean isVisible = true;

  public final String id;


  public View(View parentView, Rectangle2D.Float frameRect, String id) {
    this.frameRect = (Rectangle2D.Float)frameRect.clone();
    boundsRect = new Rectangle2D.Float(0, 0, frameRect.width, frameRect.height);
    this.id = id;

    setParentView(parentView);
  }
  

  public PVector composedScale() {
    PVector result = getScale();

    if (this.parentView != null) {
      PVector parentScale = this.parentView.composedScale();

      result.x *= parentScale.x;
      result.y *= parentScale.y;
    }

    return result;
  }

  public PVector screenSizeToViewSize(PVector size) {
    PVector result = size.copy();

    if (parentView != null) {
      result = parentView.screenSizeToViewSize(result);
    }

    PVector scale = getScale();

    result.x /= scale.x;
    result.y /= scale.y;

    return result;
  }

  public PVector getScale() {
    return new PVector(frameRect.width / boundsRect.width, frameRect.height / boundsRect.height);
  }

  public void setScale(float scale) {
    boundsRect.width = frameRect.width / scale;
    boundsRect.height = frameRect.height / scale;
  }

  public void setScale(PVector scale) {
    boundsRect.width = frameRect.width / scale.x;
    boundsRect.height = frameRect.height / scale.y;
  }

  public PVector viewSizeToScreenSize(PVector size) {
    PVector result = size.copy();

    PVector scale = getScale();
    result.x *= scale.x;
    result.y *= scale.y;

    if (parentView != null) {
      result = parentView.viewSizeToScreenSize(result);
    }

    return result;
  }

  public PVector screenPosToViewPos(PVector pos) {
    PVector result = pos.copy();

    if (parentView != null) {
      result = parentView.screenPosToViewPos(result);
    }

    result.sub(frameRect.x, frameRect.y);

    PVector scale = getScale();
    result.x /= scale.x;
    result.y /= scale.y;

    result.add(boundsRect.x, boundsRect.y);

    return result;
  }

  public PVector viewPosToScreenPos(PVector pos) {
    PVector result = pos.copy();

    result.sub(boundsRect.x, boundsRect.y);

    PVector scale = getScale();
    result.x *= scale.x;
    result.y *= scale.y;

    result.add(frameRect.x, frameRect.y);

    if (parentView != null) {
      result = parentView.viewPosToScreenPos(result);
    }

    return result;
  }

  public Rectangle2D.Float getClipBoundary() {
    Rectangle2D.Float viewClip = null;
    Rectangle2D.Float parentViewClip = null;

    if (shouldClip) {
      PVector upperLeft = viewPosToScreenPos(new PVector(boundsRect.x, boundsRect.y));
      PVector lowerRight = viewPosToScreenPos(new PVector(boundsRect.width, boundsRect.height));

      viewClip = new Rectangle2D.Float(
        upperLeft.x, 
        upperLeft.y, 
        lowerRight.x - upperLeft.x,
        lowerRight.y - upperLeft.y);
    }

    if (parentView != null) {
      parentViewClip = parentView.getClipBoundary();
    }

    if (viewClip != null && parentViewClip != null) {
      Rectangle2D intersection = parentViewClip.createIntersection(viewClip);

      return new Rectangle2D.Float(
          (float)intersection.getX(), 
          (float)intersection.getY(),
          (float)intersection.getWidth(), 
          (float)intersection.getHeight());
          
    } else if (viewClip != null) {
      return viewClip;
    } else if (parentViewClip != null) {
      return parentViewClip;
    }

    return null;
  }


  public View getViewAtPos(float x, float y) {
    Rectangle2D.Float boundary = getClipBoundary();
    View result = null;

    for (View childView : childViews) {
      result = childView.getViewAtPos(x, y);
      if (result != null) {
        break;
      }
    }

    if (result == null && (boundary == null || boundary.contains(x, y))) {
      result = this;
    }

    return result;
  }


  // ########################################################################
  // Parenting
  // ########################################################################
  final public View getParentView() {
    return parentView;
  }

  final public void setParentView(View newParentView) {
    if (parentView != newParentView) {
      if (parentView != null) {
        parentView.childViews.remove(this);
      }

      parentView = newParentView;

      if (parentView != null) {
        if (!parentView.childViews.contains(this)) {
          parentView.addChildView(this);
        }
      }
    }
  }

  final public ArrayList<View> getChildViews() {
    return childViews;
  }

  final public void addChildView(View child) {
    if(!childViews.contains(child)) {
      childViews.add(child);
    }
  }

  public void makeChildsVisible() {
    for (View child : childViews) {
      child.isVisible = true;
    }
  }

  public void makeChildsInvisible() {
    for (View child : childViews) {
      child.isVisible = false;
    }
  }

  public View findViewByID(String id) {
    if(this.id == id) {
      return this;
    }

    for(View child : childViews) {
      View result = child.findViewByID(id);
      if(result != null) {
        return result;
      }
    }

    return null;
  }


  // ########################################################################
  // Drawing
  // ########################################################################
  public final void draw() {
    if (!isVisible) {
      return;
    }

    Rectangle2D.Float clipBoundary = getClipBoundary();

    if (clipBoundary == null || (clipBoundary.width > 0 && clipBoundary.height > 0)) {
      SharedPApplet.push();

      SharedPApplet.translate(frameRect.x, frameRect.y);
      PVector scale = getScale();
      SharedPApplet.scale(scale.x, scale.y);
      SharedPApplet.translate(-boundsRect.x, -boundsRect.y);

      SharedPApplet.imageMode(PApplet.CORNER);
      if (clipBoundary != null) {
        SharedPApplet.clip(
          Math.round(clipBoundary.x), 
          Math.round(clipBoundary.y), 
          Math.round(clipBoundary.width), 
          Math.round(clipBoundary.height));
        } else {
          SharedPApplet.noClip();
      }

      beforeDrawChildren(SharedPApplet.getSharedApplet(), boundsRect.width, boundsRect.height);

      for (View childView : childViews) {
        childView.drawSiblings();
      }
      for (View childView : childViews) {
        childView.draw();
      }

      SharedPApplet.imageMode(PApplet.CORNER);
      if (clipBoundary != null) {
        SharedPApplet.clip(
          Math.round(clipBoundary.x), 
          Math.round(clipBoundary.y), 
          Math.round(clipBoundary.width), 
          Math.round(clipBoundary.height));
      } else {
        SharedPApplet.noClip();
      }

      afterDrawChildren(SharedPApplet.getSharedApplet(), boundsRect.width, boundsRect.height);

      SharedPApplet.pop();
    }
  }

  public final void drawSiblings() {
    if (!isVisible) {
      return;
    }

    Rectangle2D.Float clipBoundary = getClipBoundary();

    if (clipBoundary == null || (clipBoundary.width > 0 && clipBoundary.height > 0)) {
      SharedPApplet.push();

      SharedPApplet.translate(frameRect.x, frameRect.y);
      PVector scale = getScale();
      SharedPApplet.scale(scale.x, scale.y);
      SharedPApplet.translate(-boundsRect.x, -boundsRect.y);

      SharedPApplet.imageMode(PApplet.CORNER);
      if (clipBoundary != null) {
        SharedPApplet.clip(
          Math.round(clipBoundary.x), 
          Math.round(clipBoundary.y), 
          Math.round(clipBoundary.width), 
          Math.round(clipBoundary.height));
        } else {
          SharedPApplet.noClip();
      }

      beforeDrawSiblings(SharedPApplet.getSharedApplet(), boundsRect.width, boundsRect.height);

      SharedPApplet.pop();
    }
  }

  public void beforeDrawSiblings(PApplet p, float width, float height) {
  }

  public void beforeDrawChildren(PApplet p, float width, float height) {
  }

  public void afterDrawChildren(PApplet p, float width, float height) {
  }


  // ########################################################################
  // Mouse event
  // ########################################################################
  public final boolean processMouseEvent(MouseEvent event) {
    if(!isVisible) {
      return false;
    }

    boolean handled = false;
    
    for (int i = childViews.size() - 1; i >= 0; i--) {
      View childView = childViews.get(i);

      handled = childView.processMouseEvent(event);
      if (handled) {
          break;
      }
    }

    if(!handled) {
      handled = onMouseEvent(event);
    }

    return handled;
  }


  public boolean onMouseEvent(MouseEvent event) {
      return false;
  }


  // ########################################################################
  // Key event
  // ########################################################################
  public final boolean processKeyEvent(KeyEvent event) {
    if(!isVisible) {
      return false;
    }

    boolean handled = false;

    for (int i = childViews.size() - 1; i >= 0; i--) {
      View childView = childViews.get(i);

      handled = childView.processKeyEvent(event);
      if (handled) {
          break;
      }
    }

    if(!handled) {
      handled = onKeyEvent(event);
    }
    
    return handled;
  }


  public boolean onKeyEvent(KeyEvent event) {
      return false;
  }


  // ########################################################################
  // Resize window event
  // ########################################################################
  public final void resizeWindowEvent() {
    for (int i = childViews.size() - 1; i >= 0; i--) {
      View childView = childViews.get(i);
      childView.resizeWindowEvent();
    }

    onResizeWindowEvent();
  }


  public void onResizeWindowEvent() {
  }


  // ########################################################################
  // FrameRect
  // ########################################################################

  public void setFrameRect(double x, double y, double width, double height) {
    Rectangle2D.Float oldRect = (Rectangle2D.Float) frameRect.clone();

    frameRect.setRect(x, y, width, height);

    onFrameRectChange(oldRect);
  }

  public void setFrameRect(Rectangle2D.Float rect) {
    Rectangle2D.Float oldRect = (Rectangle2D.Float) frameRect.clone();

    frameRect.setRect(rect);

    onFrameRectChange(oldRect);
  }

  public Rectangle2D.Float getFrameRect() {
    return (Rectangle2D.Float) frameRect.clone();
  }

  public void onFrameRectChange(Rectangle2D.Float oldRect) {
  }

  // ########################################################################
  // BoundsRect
  // ########################################################################

  public void setBoundsRect(double x, double y, double width, double height) {
    boundsRect.setRect(x, y, width, height);
  }

  public void setBoundsRect(Rectangle2D.Float rect) {
    boundsRect.setRect(rect);
  }

  public Rectangle2D.Float getBoundsRect() {
    return (Rectangle2D.Float) boundsRect.clone();
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public final void destroy() {
    this.setParentView(null);

    for (Object childViewObject : (ArrayList<?>) childViews.clone()) {
      View childView = (View) childViewObject;
      childView.destroy();
    }

    onDestroy();
  }

  public void onDestroy() {
  }
}
