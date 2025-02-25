package rickAndMorty.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class Specifications {

    public static RequestSpecification baseRequestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();
    }

    public static ResponseSpecification baseResponseSpecSuccess() {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(
                        greaterThanOrEqualTo(200),
                        lessThanOrEqualTo(299)
                ))
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();
    }
}
