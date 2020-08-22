mvn \
    deploy:deploy-file \
    -Durl=file:./repo/ \
    -Dfile=/Applications/Processing.app/Contents/Java/core/library/core.jar \
    -DgroupId=org.processing \
    -DartifactId=core \
    -Dpackaging=jar \
    -Dversion=3.5.4