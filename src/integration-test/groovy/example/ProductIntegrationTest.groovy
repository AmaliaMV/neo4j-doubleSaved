package example

import grails.gorm.transactions.Rollback
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration

import geb.spock.GebSpec

@Integration
@Rollback
class ProductIntegrationTest extends GebSpec {

    void "test double saved"() {
        given:
        RestBuilder rest = new RestBuilder()

        when: "create a new Product"
        RestResponse response = rest.post("http://localhost:${serverPort}/product") {
            json([
                description: "screw"
            ])
        }

        then:
        response.status == 201
        Product product = Product.getAll().first()
        product
        product.code != Product.DEFAULT_CODE
        product.code == response.json.code

        cleanup:
        Product.withNewSession {
            Product.withNewTransaction {
                Product.getAll()*.delete()
            }
        }
    }
}
