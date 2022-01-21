package nl.basmens.diamondclicker.guiModerator.optionsGui.optionsBlock;

import java.awt.geom.Rectangle2D;

import nl.basmens.diamondclicker.bigNumber.AbbreviatedNotationStrategy;
import nl.basmens.diamondclicker.bigNumber.ScientificNotationStrategy;
import nl.basmens.diamondclicker.bigNumber.SpeechNotationStrategy;
import nl.basmens.diamondclicker.canvas.Canvas;
import nl.basmens.diamondclicker.diamondModerator.DiamondModerator;
import nl.basmens.diamondclicker.guiModerator.optionsGui.OptionsGui;
import nl.basmens.diamondclicker.guiModerator.optionsGui.OptionsGuiModelClient;
import nl.basmens.diamondclicker.mvc.*;
import nl.basmens.diamondclicker.switchButton.SwitchButton;
import nl.basmens.diamondclicker.switchButton.SwitchButtonController;
import nl.basmens.diamondclicker.switchButton.SwitchButtonModelClient;
import nl.basmens.diamondclicker.switchButton.SwitchButtonView;
import nl.basmens.diamondclicker.switchButton.SwitchButtonViewClient;

public class OptionsBlockController extends Controller implements OptionsGuiModelClient, OptionsBlockViewClient, SwitchButtonModelClient, SwitchButtonViewClient {
  public OptionsGui optionsGui;
  public OptionsBlockView optionsBlockView;

  public Canvas canvas;
  public DiamondModerator diamondModerator;

  SwitchButton toStringStrategySwitch;
  SwitchButtonView toStringStrategySwitchView;
  SwitchButton particleEffectSwitch;
  SwitchButtonView particleEffectSwitchView;


  public OptionsBlockController(Controller parentController, View parentView, OptionsGui optionsGui, Rectangle2D.Float frameRect, String id) {
    super(parentController, id);

    this.optionsGui = optionsGui;
    this.optionsGui.registerClient(this);

    optionsBlockView = new OptionsBlockView(parentView, optionsGui, frameRect, id);
    optionsBlockView.registerClient(this);

    canvas = (Canvas)optionsGui.findModelByID("canvas 0");
    diamondModerator = (DiamondModerator)optionsGui.findModelByID("diamond moderator 0");

    SwitchButtonController switchButtonController;
    toStringStrategySwitch = new SwitchButton(optionsGui, "switch button 0", "Speech", "Abbreviated", "Scientific");
    switchButtonController = new SwitchButtonController(this, optionsBlockView, toStringStrategySwitch, new Rectangle2D.Float(0, 0, 800, 80), "switch button 0");
    toStringStrategySwitchView = (SwitchButtonView)switchButtonController.getView();
    toStringStrategySwitch.registerClient(this);
    toStringStrategySwitchView.registerClient(this);

    particleEffectSwitch = new SwitchButton(optionsGui, "switch button 0", "Enable", "Disable");
    switchButtonController = new SwitchButtonController(this, optionsBlockView, particleEffectSwitch, new Rectangle2D.Float(0, 100, 800, 80), "switch button 1");
    particleEffectSwitchView = (SwitchButtonView)switchButtonController.getView();
    particleEffectSwitch.registerClient(this);
    particleEffectSwitchView.registerClient(this);
  }


  public View getView() {
    return optionsBlockView;
  }


  public void onSwitch(Model origin, int state) {
    if(origin == toStringStrategySwitch) {
      switch(state) {
        case 0:
          canvas.setDiamondsToStringStrategy(new SpeechNotationStrategy());
          break;
        case 1:
          canvas.setDiamondsToStringStrategy(new AbbreviatedNotationStrategy());
          break;
        case 2:
          canvas.setDiamondsToStringStrategy(new ScientificNotationStrategy());
          break;
      }
    } else if(origin == particleEffectSwitch) {
      switch(state) {
        case 0:
          diamondModerator.enableShineEffect();
          break;
        case 1:
          diamondModerator.disableShineEffect();
          break;
      }
    }
  }


  // ########################################################################
  // Destruction
  // ########################################################################
  public void onDestroy() {
    optionsGui.unregisterClient(this);
    optionsBlockView.unregisterClient(this);

    toStringStrategySwitch.unregisterClient(this);
    toStringStrategySwitchView.unregisterClient(this);

    optionsBlockView.destroy();
  }

  public void onOptionsGuiModelDestroy() {
    optionsGui = null;
  }

  public void onOptionsBlockViewDestroy() {
    optionsBlockView = null;
  }

  public void onSwitchButtonModelDestroy(Model origin) {
    if(origin == toStringStrategySwitch) {
      toStringStrategySwitch = null;
    }
  }

  public void onSwitchButtonViewDestroy(View origin) {
    if(origin == toStringStrategySwitchView) {
      toStringStrategySwitchView = null;
    }
  }
}
