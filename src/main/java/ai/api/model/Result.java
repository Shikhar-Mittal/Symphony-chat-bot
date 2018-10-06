package ai.api.model;

/***********************************************************************************************************************
 *
 * API.AI Java SDK - client-side libraries for API.AI
 * =================================================
 *
 * Copyright (C) 2014 by Speaktoit, Inc. (https://www.speaktoit.com)
 * https://www.api.ai
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ai.api.util.StringUtils;

import ai.api.util.ParametersConverter;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private static final String DATE_FORMAT_ERROR_MESSAGE = "'%s' parameter has value '%s' and can't be parsed as a Date or Time";

    @SerializedName("action")
    private String action;

    @SerializedName("score")
    private float score;
	
    @SerializedName("source")
    private String source;

    /**
     * This field will be deserialized as hashMap container with all parameters and it's values
     */
    /**
     * 
     */
//    @SerializedName("parameters")
//    private HashMap<String, JsonElement> parameters;

    private Parameters parameters;

    
    /**
     * Currently active contexts
     */
    @SerializedName("contexts")
    private List<AIOutputContext> contexts;


    @SerializedName("metadata")
    private Metadata metadata;

    /**
     * The query that was used to produce this result
     */
    @SerializedName("resolvedQuery")
    private String resolvedQuery;

    /**
     * Fulfillment of the response
     */
    @SerializedName("fulfillment")
    private Fulfillment fulfillment;

    @SerializedName("actionIncomplete")
    private boolean actionIncomplete;
    
    public Parameters getParameters ()
    {
        return parameters;
    }

    public void setParameters (Parameters parameters)
    {
        this.parameters = parameters;
    }

    public String getAction() {
        if (action == null) {
            return "";
        }
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }
	
    public float getScore() {
	return score;
    }

    public void setScore(float score) {
	this.score = score;
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public void setResolvedQuery(final String resolvedQuery) {
        this.resolvedQuery = resolvedQuery;
    }

    public Fulfillment getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(final Fulfillment fulfillment) {
        this.fulfillment = fulfillment;
    }

    public boolean isActionIncomplete() {
        return actionIncomplete;
    }

    public void setActionIncomplete(final boolean actionIncomplete) {
        this.actionIncomplete = actionIncomplete;
    }

    @Override
    public String toString() {
        return String.format("Result {action='%s', resolvedQuery='%s'}",
                action,
                resolvedQuery);
    }
}
