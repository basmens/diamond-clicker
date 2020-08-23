package nl.benmens.evolution.factories;

import nl.benmens.evolution.entity.EntityController;
import nl.benmens.evolution.entity.EntityInterface;
import nl.benmens.evolution.world.World;
import nl.benmens.evolution.world.WorldController;
import nl.benmens.evolution.world.terrain.grass.Grass;
import nl.benmens.evolution.world.terrain.grass.GrassController;
import nl.benmens.evolution.world.terrain.water.Water;
import nl.benmens.evolution.world.terrain.water.WaterController;
import nl.benmens.processing.mvc.Controller;
import nl.benmens.processing.mvc.View;

public class ControlerFactory {

  public ControlerFactory() {}

  public Controller createController(Controller parentCotroller, View parentView, Object model) {
    if (model instanceof Grass) {
      Grass grass = (Grass)model;

      return new GrassController(parentCotroller, parentView, grass);
    } else if (model instanceof Water) {
      Water grass = (Water)model;

      return new WaterController(parentCotroller, parentView, grass);
    } else if (model instanceof EntityInterface) {
      EntityInterface entity = (EntityInterface)model;

      return new EntityController(parentCotroller, parentView, entity);
    } else if (model instanceof World) {
      World world = (World)model;

      return new WorldController(parentCotroller, parentView, world);
    }

    return null;
  }
  
}