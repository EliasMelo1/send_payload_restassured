package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;

import models.request.LoginPayload;
import models.request.Seguradora;
import models.response.LoginSucesso;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import requests.Post;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LoginTest {

    Post post = new Post();

    @Test(description = "02. Envio body restassured")
    // TODO DOC: https://github.com/rest-assured/rest-assured/wiki/usage#object-mapping
    public void envioPayloadString(){
        String bodyString = "{\n" +
                "  \"email\": \"fulano@qa.com\",\n" +
                "  \"password\": \"teste\"\n" +
                "}";

        String nome = "Elias";

        LoginPayload bodyModel = new LoginPayload("fulano@qa.com","teste");

        // TODO executar teste com contentType comentado e informando bodyString (status 400, verificar mensagem e body enviado no request)
        // TODO executar teste com contentType descomentado e informando bodyString (status 200, verificar sucesso e body enviado no request)
        // TODO executar teste enviando a variavel nome e com contentype (status 500, verificar mensagem e body enviado no request)
        // TODO executar teste enviando a variavel bodyModel e com contentType comentado (status 400, verificar mensagem e body enviado no request)
        // TODO executar teste enviando a variavel bodyModel e com contentType descomentado (status 200, verificar sucesso e body enviado no request)
        LoginPayload loginPayload = new LoginPayload("fulano@qa.com", "teste");

        baseURI = "https://serverest.dev";
        given().
                contentType(ContentType.JSON).
                log().all().
                body(bodyModel).
                when().
                post("/login").
                then().
                log().all().
                statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "03. Envio file como String")
    public void envioPayloadFile() throws IOException {
        String fileBody = new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/loginSucessoPayload.json")));

        baseURI = "https://serverest.dev";
        given().
                contentType(ContentType.JSON).
                log().all().
                body(fileBody).
                when().
                post("/login").
                then().
                log().all().
                statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "04. Envio de Objeto buildado")
    public void envioPayloadObjetoBuildado()  {
        LoginPayload bodyBuilder = LoginPayload.builder().
                email("fulano@qa.com").
                password("teste").
                build();

        post.login(bodyBuilder).statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "05. Envio de Objeto contruido")
    public void envioPayloadObjetoModel() {
        LoginPayload bodyModel = new LoginPayload("fulano@qa.com", "teste");
        post.login(bodyModel).statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "06. Envio de objeto serializado por file")
    public void envioPayloadFileObject() throws IOException {
        String fileBody = new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/loginSucessoPayload.json")));
        LoginPayload bodyFileModel = new Gson().fromJson(fileBody, LoginPayload.class);
        post.login(bodyFileModel).statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "07. Extra")
    public void extrairObjetoRequest(){
        LoginPayload bodyModel = new LoginPayload("fulano@qa.com", "teste");
        LoginSucesso responseBody = post.login(bodyModel).statusCode(HttpStatus.SC_OK).extract().response().as(LoginSucesso.class);
        System.out.println("\n \n \nMessagem retornada no body: "+responseBody.getMessage());
        responseBody.toString();
    }
}
