FROM	java:8-alpine
RUN	apk update && apk add --no-cache git \
	&& cd / \
	&& git clone https://github.com/ConfigHubPub/Database-Manager.git \
	&& cd Database-Manager/ \ 
	&& ./gradlew clean build jar --no-build-cache \
	&& database_manager=`ls /Database-Manager/build/libs/` \
	&& mv /Database-Manager/build/libs/$database_manager /Database-Manager.jar \
	&& mv /Database-Manager/dbm.sh /dbm.sh \
	&& chmod a+x /dbm.sh \
	&& rm -rf /Database-Manager/ \
	&& rm -rf /var/cache/apk/* \
	&& rm -rf ~/.gradle
            
ENTRYPOINT  ["/dbm.sh"]
