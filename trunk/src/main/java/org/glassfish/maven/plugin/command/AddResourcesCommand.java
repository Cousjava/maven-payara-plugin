/*******************************************************************************
 *
 * Copyright (c) 2007, Dave Whitla
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of the copyright holder nor the names of contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 ******************************************************************************/

package org.glassfish.maven.plugin.command;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dwhitla at Apr 9, 2007 4:26:46 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 * @version $Id: AddResourcesCommand.java 0 Apr 9, 2007 4:26:46 PM dwhitla $
 */
public class AddResourcesCommand extends InteractiveAsadminCommand {

    private Domain domain;

    public AddResourcesCommand(GlassfishMojo sharedContext, Domain domain) {
        super(sharedContext);
        this.domain = domain;
    }

    protected String getName() {
        return "add-resources";
    }

    protected List<String> getParameters() {
        if (domain.getResourceDescriptor() == null) {
            return null;
        }
        List<String> parameters = super.getParameters();
        parameters.addAll(Arrays.asList(
                "--port", String.valueOf(domain.getAdminPort()),
                "--target", "domain",
                domain.getResourceDescriptor()
        ));
        return parameters;
    }

    protected String getErrorMessage() {
        return "Unable to add resources for domain \"" + domain.getName() + "\".";
    }
}
