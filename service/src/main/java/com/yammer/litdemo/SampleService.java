package com.yammer.litdemo;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.litdemo.resources.SampleProductResource;

public class SampleService extends Service<SampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new SampleService().run(args);
    }

    @Override
    public void initialize(Bootstrap<SampleConfiguration> sampleConfigurationBootstrap) {

    }

    @Override
    public void run(SampleConfiguration sampleConfiguration, Environment environment) throws Exception {
        environment.addResource(new SampleProductResource(new UserDao()));
    }
}
