rootProject.name = "practicum-bank"

include("services:account")
include("services:blocker")
include("services:cash")
include("services:exchange-generator")
include("services:exchange")
include("services:front-ui")
include("services:notification")
include("services:transfer")
include("common")

include("clients:account")
include("clients:cash")
include("clients:exchange")
include("clients:transfer")
include("clients:blocker")
include("clients:notification")