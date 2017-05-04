/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2007-2008 maven-glassfish-plugin developers. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by the copyright holder in the GPL Version 2 section of the
 * License file that accompanied this code.  If applicable, add the following
 * below the License Header, with the fields enclosed by brackets [] replaced
 * by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 ******************************************************************************/

package fish.payara.maven.plugin.command;

import fish.payara.maven.plugin.Domain;
import fish.payara.maven.plugin.GlassfishMojo;

import java.util.List;

/**
 * Created by dwhitla at Apr 9, 2007 4:09:05 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 * @version $Id: CreateDomainCommand.java 0 Apr 9, 2007 4:09:05 PM dwhitla $
 */
public class CreateDomainCommand extends InteractiveAsadminCommand {

    private Domain domain;

    public CreateDomainCommand(GlassfishMojo sharedContext, Domain domain) {
        super(sharedContext);
        this.domain = domain;
    }

    protected String getName() {
        return "create-domain";
    }

    protected List<String> getParameters() {
        StringBuilder domainProperties = new StringBuilder();
        if (domain.getHTTPSPort() > 0) {
            domainProperties.append("http.ssl.port=").append(domain.getHTTPSPort());
        }
        if (domain.getIIOPPort() > 0) {
            if (domainProperties.length() > 0) {
                domainProperties.append(":");
            }
            domainProperties.append("orb.listener.port=").append(domain.getIIOPPort());
        }
        if (domain.getJMSPort() > 0) {
            if (domainProperties.length() > 0) {
                domainProperties.append(":");
            }
            domainProperties.append("jms.port=").append(domain.getJMSPort());
        }

        List<String> parameters = super.getParameters();
        addOptionalParameter(parameters, "--domaindir", domain.getDirectory());
        addOptionalParameter(parameters, "--profile", domain.getProfile());
        addOptionalParameter(parameters, "--adminport", domain.getAdminPort());
        addOptionalParameter(parameters, "--instanceport", domain.getHTTPPort());
        addOptionalParameter(parameters, "--domainproperties", domainProperties.toString());
        addOptionalParameter(parameters, "--savemasterpassword", "true");
        parameters.add(domain.getName());
        return parameters;
    }

    protected String getErrorMessage() {
        return "Unable to create domain \"" + domain.getName() + "\".";
    }
}
