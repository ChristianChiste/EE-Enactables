package at.uibk.dps.ee.enactables.wrapperSkeletton;

import org.opt4j.core.config.annotations.Info;
import org.opt4j.core.config.annotations.Order;
import org.opt4j.core.start.Constant;

import at.uibk.dps.ee.enactables.EnactableFactory;
import at.uibk.dps.ee.guice.modules.EeModule;

public class EnactableFactoryModule extends EeModule {

	@Order(1)
	@Info("If checked, the enactables fail with a certain probability")
	protected boolean useReliabilityWrapper = true;

	@Order(2)
	@Info("The fail rate of the enactables")
	@Constant(namespace = EnactableFactoryDecorator.class, value = "faultRate")
	protected double failRate = .5;

	@Override
	protected void config() {
		if (useReliabilityWrapper) {
			bind(FactoryInterface.class).to(EnactableFactoryDecorator.class);
		} else {
			bind(FactoryInterface.class).to(EnactableFactory.class);
		}
	}

	public boolean isUseReliabilityWrapper() {
		return useReliabilityWrapper;
	}

	public void setUseReliabilityWrapper(boolean useReliabilityWrapper) {
		this.useReliabilityWrapper = useReliabilityWrapper;
	}

	public double getFailRate() {
		return failRate;
	}

	public void setFailRate(double failRate) {
		this.failRate = failRate;
	}

}
