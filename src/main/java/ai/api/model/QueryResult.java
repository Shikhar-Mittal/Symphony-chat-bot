package ai.api.model;

public class QueryResult
{
    private String languageCode;

    private String intentDetectionConfidence;

    private String allRequiredParamsPresent;

    private String action;

    private String fulfillmentText;

    private Parameters parameters;

    private FulfillmentMessages[] fulfillmentMessages;

    private String queryText;

    private String diagnosticInfo;

    private Intent intent;

    public String getLanguageCode ()
    {
        return languageCode;
    }

    public void setLanguageCode (String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getIntentDetectionConfidence ()
    {
        return intentDetectionConfidence;
    }

    public void setIntentDetectionConfidence (String intentDetectionConfidence)
    {
        this.intentDetectionConfidence = intentDetectionConfidence;
    }

    public String getAllRequiredParamsPresent ()
    {
        return allRequiredParamsPresent;
    }

    public void setAllRequiredParamsPresent (String allRequiredParamsPresent)
    {
        this.allRequiredParamsPresent = allRequiredParamsPresent;
    }

    public String getAction ()
    {
        return action;
    }

    public void setAction (String action)
    {
        this.action = action;
    }

    public String getFulfillmentText ()
    {
        return fulfillmentText;
    }

    public void setFulfillmentText (String fulfillmentText)
    {
        this.fulfillmentText = fulfillmentText;
    }

    public Parameters getParameters ()
    {
        return parameters;
    }

    public void setParameters (Parameters parameters)
    {
        this.parameters = parameters;
    }

    public FulfillmentMessages[] getFulfillmentMessages ()
    {
        return fulfillmentMessages;
    }

    public void setFulfillmentMessages (FulfillmentMessages[] fulfillmentMessages)
    {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    public String getQueryText ()
    {
        return queryText;
    }

    public void setQueryText (String queryText)
    {
        this.queryText = queryText;
    }

    public String getDiagnosticInfo ()
    {
        return diagnosticInfo;
    }

    public void setDiagnosticInfo (String diagnosticInfo)
    {
        this.diagnosticInfo = diagnosticInfo;
    }

    public Intent getIntent ()
    {
        return intent;
    }

    public void setIntent (Intent intent)
    {
        this.intent = intent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [languageCode = "+languageCode+", intentDetectionConfidence = "+intentDetectionConfidence+", allRequiredParamsPresent = "+allRequiredParamsPresent+", action = "+action+", fulfillmentText = "+fulfillmentText+", parameters = "+parameters+", fulfillmentMessages = "+fulfillmentMessages+", queryText = "+queryText+", diagnosticInfo = "+diagnosticInfo+", intent = "+intent+"]";
    }
}