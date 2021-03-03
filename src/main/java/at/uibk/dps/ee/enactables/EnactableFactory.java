package at.uibk.dps.ee.enactables;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import at.uibk.dps.ee.core.enactable.Enactable;
import at.uibk.dps.ee.core.enactable.EnactableStateListener;
import at.uibk.dps.ee.enactables.wrapper.FactoryInterface;
import at.uibk.dps.ee.model.properties.PropertyServiceFunction;
import net.sf.opendse.model.Task;

/**
 * The {@link EnactableFactory} is used for the creation of elemental
 * {@link Enactable}s.
 * 
 * @author Fedor Smirnov
 *
 */
@Singleton
public class EnactableFactory implements FactoryInterface{

  protected final Set<EnactableStateListener> stateListeners;

  /**
   * The factory is provided with a list of listeners which are to be added to
   * every created enactable.
   * 
   * @param stateListeners a list of listeners which are to be added to every
   *        created enactable
   */
  @Inject
  public EnactableFactory(final Set<EnactableStateListener> stateListeners) {
    this.stateListeners = new HashSet<>();
    this.stateListeners.addAll(stateListeners);
  }

  @Override
  public void addEnactableStateListener(final EnactableStateListener listener) {
    this.stateListeners.add(listener);
  }

  @Override
  public EnactableAtomic createEnactable(final Task functionNode) {
    return new EnactableAtomic(stateListeners, functionNode);
  }

  @Override
  public void reproduceEnactable(final Task offspring, final EnactableAtomic parentEnactable) {
    final EnactableAtomic offspringEnactable = createEnactable(offspring);
    final JsonObject parentInput =
        Optional.ofNullable(parentEnactable.getInput()).orElseGet(() -> new JsonObject());
    parentInput.keySet()
        .forEach(key -> offspringEnactable.setInputValue(key, parentInput.get(key)));
    PropertyServiceFunction.setEnactable(offspring, offspringEnactable);
  }
}
