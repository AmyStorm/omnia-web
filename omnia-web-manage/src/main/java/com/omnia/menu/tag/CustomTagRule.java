package com.omnia.menu.tag;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * custom tag decoration for sitemesh.
 * Created by khaerothe on 2015/5/7.
 */
public class CustomTagRule implements TagRuleBundle{
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("mainTitle", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("mainTitle"), false));
        defaultState.addRule("mainDescription", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("mainDescription"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

    }
}
