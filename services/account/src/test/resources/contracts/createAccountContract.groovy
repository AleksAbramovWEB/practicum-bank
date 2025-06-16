package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Создание нового счёта")

    request {
        method 'POST'
        urlPath('/user')

        headers {
            contentType(applicationJson())
            header('Authorization', value(consumer(regex('Bearer .+')), producer('Bearer test-token')))
        }

        body(
                currency: $(consumer(regex('RUB|USD|EUR')), producer('RUB'))
        )
    }

    response {
        status 201
        headers {
            contentType(applicationJson())
        }
    }
}
