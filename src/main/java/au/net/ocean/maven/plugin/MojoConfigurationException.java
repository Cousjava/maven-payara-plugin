/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.net.ocean.maven.plugin;

import java.util.List;
import org.apache.maven.plugin.AbstractMojoExecutionException;
import fish.payara.maven.plugin.GlassfishMojo;

/**
 *
 * @author jonathan
 */
public class MojoConfigurationException extends Exception{
    
    public MojoConfigurationException(String message) {
        super(message);
    }

    public MojoConfigurationException(GlassfishMojo aThis, List<String> asList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
