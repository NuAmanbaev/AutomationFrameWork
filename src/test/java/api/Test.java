package api;

import com.github.javafaker.Faker;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.json.Json;
import utilities.CashWiseToken;
import utilities.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {


    @org.junit.Test
    public void testToken(){

        RequestBody requestBody = new RequestBody();


        String endPoint = "https://backend.cashwise.us/api/myaccount/auth/login";



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

    String expectedEmail = response.jsonPath().getString("email");

    Assert.assertFalse(expectedEmail.isEmpty());
    Assert.assertTrue(expectedEmail.endsWith(".com"));



}

@org.junit.Test

    public void  CreateSincleSeller() {
    Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();

        String endpoint1 = "/api/myaccount/sellers";

        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers";

        String token = CashWiseToken.GetToken();

        requestBody.setCompanyName(faker.name().fullName());
        requestBody.setSellerName(faker.name().username());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhoneNumber(faker.number().randomDigit());
        requestBody.setAddress(faker.address().fullAddress());


        Response response1 = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);

            int statusCode1 = response1.statusCode();

    System.out.println(statusCode1);

    response1.prettyPrint();





}


@org.junit.Test

    public void GetAllSellers(){

        String url = Config.getProperty("cashWiseApiUrl") +  "/api/myaccount/sellers";
        String token = CashWiseToken.GetToken();


        Map<String,Object > params = new HashMap<>();
        params.put("isArchived", false);
        params.put("size",10);
        params.put("page", 1);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);

        int statuscode = response.statusCode();

        Assert.assertEquals(200, statuscode);
       // response.prettyPrint();

        String email = response.jsonPath().getString("responses[0].email");
        Assert.assertFalse(email.isEmpty());

        String email3 = response.jsonPath().getString("responses[2].email");
        String email4 = response.jsonPath().getString("responses[3].email");


}




    @org.junit.Test
    public void GetAllSellersLoop(){

    String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers";
    String token = CashWiseToken.GetToken();

    Map<String, Object> lParams = new HashMap<>();
    lParams.put("isArchived", false);
    lParams.put("size", 10);
    lParams.put("page", 1);


    Response response = RestAssured.given().auth().oauth2(token).params(lParams).get(url);

    int statuscode = response.getStatusCode();

    Assert.assertEquals(200, statuscode);

        List<String> ListofEmails = response.jsonPath().getList("responses.email");

        for (String email: ListofEmails) {
            Assert.assertFalse(email.isEmpty());
        }






}











}
