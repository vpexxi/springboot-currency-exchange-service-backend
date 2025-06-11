FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/currency-exchange-app

COPY target target

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jre-slim
MAINTAINER drillinsight

VOLUME /app/currency-exchange-app
ARG DEPENDENCY=/workspace/currency-exchange-app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp","app:app/lib/*","com.drill.currencyexchangeapp.CurrencyExchangeAppApplication"]