package at.uibk.dps.ee.enactables.schedule;

import java.util.HashSet;
import java.util.Set;
import com.google.inject.Inject;
import at.uibk.dps.ee.model.graph.SpecificationProvider;
import net.sf.opendse.model.Mapping;
import net.sf.opendse.model.Resource;
import net.sf.opendse.model.Task;

/**
 * The {@link SchedulerSingleOption} is used in cases where exactly one mapping is provided for each
 * task (enactments which are to run with the specified binding, without any scheduling decisions).
 * 
 * @author Fedor Smirnov
 */
public class SchedulerSingleOption extends SchedulerAbstract {

  @Inject
  public SchedulerSingleOption(SpecificationProvider specProvider) {
    super(specProvider);
  }

  @Override
  protected Set<Mapping<Task, Resource>> chooseMappingSubset(Task task,
      Set<Mapping<Task, Resource>> mappingOptions) {
    if (mappingOptions.size() != 1) {
      throw new IllegalArgumentException("More than one mapping provided for task " + task.getId());
    }
    return new HashSet<>(mappingOptions);
  }
}