package br.com.lucaslib.configuration;

import java.io.InputStream;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import br.com.lucaslib.configuration.annotation.Configuration;

public class ConfigurationFactory {

	@Produces
	@Configuration
	@ApplicationScoped
	public Properties getProperties() throws Exception {
		InputStream inputStream = ConfigurationFactory.class.getResourceAsStream("/lucaslib.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		return properties;
	}
}