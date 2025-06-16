package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Получить список счетов текущего пользователя")

    request {
        method 'GET'
        urlPath('/user')

        headers {
            header('Authorization', value(consumer(regex('Bearer .+')), producer('Bearer test-token')))
        }
    }

    response {
        status 200
    }
}
