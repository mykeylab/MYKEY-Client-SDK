#!/bin/bash
echo $1

function funBuildAndInstall(){
    case $4 in
        -install)
            echo "start install"
            adb install -r -d $2
            echo "install ok"
        ;;
        -build)
            echo "start build"
            ./gradlew $3
            echo "build ok"
        ;;
        *)
            funBuildAndInstall $1 $2 $3 -build
            funBuildAndInstall $1 $2 $3 -install
        ;;
    esac
}

case $1 in
    release) funBuildAndInstall com.mykey.app app/build/outputs/apk/release/app-release.apk assembleRelease $2 prod
    ;;
    pre) funBuildAndInstall com.mykey.app app/build/outputs/apk/pre/app-pre.apk assemblePre $2 dev
    ;;
    testa) funBuildAndInstall com.mykey.app app/build/outputs/apk/atest/app-testa.apk assembleAtest $2 dev
    ;;
    debug) funBuildAndInstall com.mykey.app app/build/outputs/apk/debug/app-debug.apk assembleDebug $2 dev
    ;;

    *)
    echo "no command"
    ;;
esac
