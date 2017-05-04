package au.net.ocean.maven.plugin;

import au.net.ocean.maven.plugin.annotation.Parameter;
import au.net.ocean.maven.plugin.annotation.ReadOnly;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

public abstract class OceanMojo extends AbstractMojo {

    @ReadOnly
    @Parameter(expression = "${project}")
    private MavenProject project;
    @Parameter(description = "Skip execution", defaultValue = "false")
    private boolean skip;

    public OceanMojo() {
        super();
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public final void execute() throws MojoExecutionException, MojoFailureException {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      au/net/ocean/maven/plugin/OceanMojo.skip:Z
         * 4: ifne          15
         * 7: aload_0
         * 8: invokevirtual au/net/ocean/maven/plugin/OceanMojo.postConfig:()V
         * 11: aload_0
         * 12: invokevirtual au/net/ocean/maven/plugin/OceanMojo.doExecute:()V
         * 15: return
         *  */
        // </editor-fold>
        if (!skip){
            try {
                postConfig();
            } catch (MojoConfigurationException ex) {
                Logger.getLogger(OceanMojo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        doExecute();
        
    }

    protected abstract void postConfig() throws MojoConfigurationException;

    protected abstract void doExecute() throws MojoExecutionException, MojoFailureException;

    protected abstract String getPrefix();
}
