package bzh.ya2o;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.api.container.filter.GZIPContentEncodingFilter;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import javax.servlet.annotation.*;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class Configuration extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(

                new JerseyServletModule() {
                    @Override
                    protected void configureServlets() {
                        ResourceConfig config = new PackagesResourceConfig(WebService.class.getPackage().getName());
                        for (Class<?> resource : config.getClasses()) {
                            bind(resource);
                        }

                        Map<String, String> params = new HashMap<String, String>();
                        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, WebService.class.getPackage().getName());
                        params.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, GZIPContentEncodingFilter.class.getName());
                        params.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, GZIPContentEncodingFilter.class.getName());

                        serve("/*").with(GuiceContainer.class, params);
                    }
                },

                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(new TypeLiteral<Dao<String>>() {}).to(FooDao.class);
                    }
                }
        );
    }
}
