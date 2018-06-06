package example

import grails.rest.*

import static org.springframework.http.HttpStatus.CREATED

class ProductController extends RestfulController {

    static responseFormats = ['json', 'xml']

    ProductController() {
        super(Product)
    }

    def save() {
        Product instance = (Product) createResource()

        if (instance.validate()) {
            instance.save()
            instance.code = String.format("%014d", instance.id)
            instance.save()
        }

        respond instance, [status: CREATED]
    }
}
