package ai.api.model;

public class Response
{
    private String responseId;

    private QueryResult queryResult;

    public String getResponseId ()
    {
        return responseId;
    }

    public void setResponseId (String responseId)
    {
        this.responseId = responseId;
    }

    public QueryResult getQueryResult ()
    {
        return queryResult;
    }

    public void setQueryResult (QueryResult queryResult)
    {
        this.queryResult = queryResult;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responseId = "+responseId+", queryResult = "+queryResult+"]";
    }
}
	