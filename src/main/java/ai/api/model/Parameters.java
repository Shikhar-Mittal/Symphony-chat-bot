package ai.api.model;

public class Parameters {
	private String Quantity;

    private String Company;

    private String Fallback;
    
    private String MarketPrice;
    
    private String Action;
    
    private String[] Parameter;
    
    private String[] Database;

    public String getMarketPrice()
    {
    	return MarketPrice;
    }
    
    public void setMarketPrice(String MarketPrice)
    {
    	this.MarketPrice = MarketPrice;
    }
    
    public String getAction()
    {
    	return Action;
    }
    
    public void setAction(String Action)
    {
    	this.Action = Action;
    }
    
    public String[] getDatabase()
    {
    	return Database;
    }
    
    public void setDatabase (String[] Database)
    {
    	this.Database = Database;
    }

    public String getFallBack() 
    {
    	return Fallback;
    }
    
    public void setFallback (String Fallback)
    {
    	this.Fallback = Fallback;
    }
    
    public String getQuantity ()
    {
        return Quantity;
    }

    public void setQuantity (String Quantity)
    {
        this.Quantity = Quantity;
    }

    public String getCompany ()
    {
        return Company;
    }

    public void setCompany (String Company)
    {
        this.Company = Company;
    }

    public String[] getParameter ()
    {
        return Parameter;
    }

    public void setParameter (String[] Parameter)
    {
        this.Parameter = Parameter;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Quantity = "+Quantity+", Company = "+Company+", Parameter = "+Parameter+"]";
    }
}