package example

class Product {

    final static String DEFAULT_CODE = "XXXX-XXXX-XXXX"

    String code = DEFAULT_CODE
    String description

    static constraints = {
        code size: 14..14
    }
}
