package worldBank

object Helper {
  
  val baseURL = "http://api.worldbank.org/"
  
  def getIndicatorByCountryURL(country: String, indicator: String) = 
    s"$baseURL/countries/$country/indicators/$indicator"
      
}