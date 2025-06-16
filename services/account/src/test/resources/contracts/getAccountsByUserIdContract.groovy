package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Получить счета по ID пользователя")

    request {
        method 'GET'
        urlPath($(consumer(regex('/user/.+')), producer('/user/user-456')))

        headers {
            header('Authorization', value(consumer(regex('Bearer .+')), producer('Bearer test-token')))
        }
    }

    response {
        status 200
        headers {
            contentType(applicationJson())
        }
    }
}
