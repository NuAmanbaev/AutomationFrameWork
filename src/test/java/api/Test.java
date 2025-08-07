package api;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.CashWiseToken;
import utilities.Config;

public class Test {

    @org.junit.Test
    public void testToken(){
        String endPoint = "https://backend.cashwise.us/api/myaccount/auth/login";


        RequestBody requestBody = new RequestBody();
        requestBody.setEmail(Config.getProperty("loginEmail"));
        requestBody.setPassword(Config.getProperty("loginPassword"));

       Response response = RestAssured.given().contentType(ContentType.JSON)
                .body(requestBody).post(endPoint);

       int statusCode = response.statusCode();

        Assert.assertEquals(200, statusCode);

        System.out.println(statusCode);

      //  response.prettyPrint();

        String token = response.jsonPath().getString("jwt_token");
        System.out.println(token);




}

@org.junit.Test
    public void GetSingleSeller(){

    String url = Config.getProperty("cashWiseApiUrl")  + "/api/myaccount/sellers/" + 110;
    String token = CashWiseToken.GetToken();

    Response response = RestAssured.given().auth().oauth2(token).get(url);
    response.prettyPrint();


}





}
