package com.if.serviceability.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.if.serviceability.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/Serviceability/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/Serviceability/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("Serviceability", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreateVerficationInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("VerficationInstance.json"))
        .when()
        .post("/Serviceability/Verfications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsVerfication() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("VerficationInstance.json"))
        .when()
        .post("/Serviceability/Verfications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/Verfications?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).VerificationID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/Verfications/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreatePaymentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PaymentInstance.json"))
        .when()
        .post("/Serviceability/Payments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPayment() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PaymentInstance.json"))
        .when()
        .post("/Serviceability/Payments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/Payments?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).PaymentID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/Payments/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateCustomerInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("CustomerInstance.json"))
        .when()
        .post("/Serviceability/Customers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsCustomer() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("CustomerInstance.json"))
        .when()
        .post("/Serviceability/Customers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/Customers?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CustomerID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/Customers/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateInsuranceInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("InsuranceInstance.json"))
        .when()
        .post("/Serviceability/Insurances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsInsurance() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("InsuranceInstance.json"))
        .when()
        .post("/Serviceability/Insurances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/Insurances?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).InsuranceID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/Insurances/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateServiceCrewInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ServiceCrewInstance.json"))
        .when()
        .post("/Serviceability/ServiceCrews")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsServiceCrew() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ServiceCrewInstance.json"))
        .when()
        .post("/Serviceability/ServiceCrews")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/ServiceCrews?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ServiceID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/ServiceCrews/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateReturnRentedBikeInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ReturnRentedBikeInstance.json"))
        .when()
        .post("/Serviceability/ReturnRentedBikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsReturnRentedBike() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ReturnRentedBikeInstance.json"))
        .when()
        .post("/Serviceability/ReturnRentedBikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/ReturnRentedBikes?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BookingID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/ReturnRentedBikes/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateBookingInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("BookingInstance.json"))
        .when()
        .post("/Serviceability/Bookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsBooking() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("BookingInstance.json"))
        .when()
        .post("/Serviceability/Bookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/Bookings?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BookingID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/Bookings/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateRoadsideAssistanceInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("RoadsideAssistanceInstance.json"))
        .when()
        .post("/Serviceability/RoadsideAssistances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsRoadsideAssistance() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("RoadsideAssistanceInstance.json"))
        .when()
        .post("/Serviceability/RoadsideAssistances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/RoadsideAssistances?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CustomerID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/RoadsideAssistances/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateExtendBookingInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ExtendBookingInstance.json"))
        .when()
        .post("/Serviceability/ExtendBookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsExtendBooking() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ExtendBookingInstance.json"))
        .when()
        .post("/Serviceability/ExtendBookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/ExtendBookings?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BookingID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/ExtendBookings/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateRentalCompanyInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("RentalCompanyInstance.json"))
        .when()
        .post("/Serviceability/RentalCompanies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsRentalCompany() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("RentalCompanyInstance.json"))
        .when()
        .post("/Serviceability/RentalCompanies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/RentalCompanies?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CompanyID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/RentalCompanies/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateReturnBikeInspectionInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ReturnBikeInspectionInstance.json"))
        .when()
        .post("/Serviceability/ReturnBikeInspections")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsReturnBikeInspection() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ReturnBikeInspectionInstance.json"))
        .when()
        .post("/Serviceability/ReturnBikeInspections")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/ReturnBikeInspections?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BikeID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/ReturnBikeInspections/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateBikeInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("BikeInstance.json"))
        .when()
        .post("/Serviceability/Bikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsBike() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("BikeInstance.json"))
        .when()
        .post("/Serviceability/Bikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/Serviceability/Bikes?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BikeID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/Serviceability/Bikes/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM serviceability.Verfication");
    jdbcTemplate.execute("DELETE FROM serviceability.Payment");
    jdbcTemplate.execute("DELETE FROM serviceability.Customer");
    jdbcTemplate.execute("DELETE FROM serviceability.Insurance");
    jdbcTemplate.execute("DELETE FROM serviceability.ServiceCrew");
    jdbcTemplate.execute("DELETE FROM serviceability.ReturnRentedBike");
    jdbcTemplate.execute("DELETE FROM serviceability.Booking");
    jdbcTemplate.execute("DELETE FROM serviceability.RoadsideAssistance");
    jdbcTemplate.execute("DELETE FROM serviceability.ExtendBooking");
    jdbcTemplate.execute("DELETE FROM serviceability.RentalCompany");
    jdbcTemplate.execute("DELETE FROM serviceability.ReturnBikeInspection");
    jdbcTemplate.execute("DELETE FROM serviceability.Bike");
     jdbcTemplate.execute("DELETE FROM serviceability.ReturnRentedBikeInspection");
     jdbcTemplate.execute("DELETE FROM serviceability.CustomerExtendRental");
     jdbcTemplate.execute("DELETE FROM serviceability.RentalCompanyBookings");
     jdbcTemplate.execute("DELETE FROM serviceability.RentalCompanyCustomers");
     jdbcTemplate.execute("DELETE FROM serviceability.CustomerBookings");
     jdbcTemplate.execute("DELETE FROM serviceability.RentalCompanyBikes");
     jdbcTemplate.execute("DELETE FROM serviceability.CustomerReturns");

    RestAssuredMockMvc.reset();
  }
}
