#!/bin/sh

function get_project_info(){  
  if [ "$DEBUG" == "true" ]; then
    xmllint --format pom.xml
  fi
  get_project_name
  get_project_version
}

function get_project_name(){
  PROJECT_NAME=$(xmllint --xpath '/*[local-name()="project"]/*[local-name()="artifactId"]/text()' pom.xml)
}

function get_project_version(){
  PROJECT_VERSION=$(xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' pom.xml)
}

function write_to_env_file(){
  echo 'PROJECT_NAME='${PROJECT_NAME} >> .env
  echo 'PROJECT_VERSION='${PROJECT_VERSION} >> .env
  if [ "$DEBUG" == "true" ]; then
    cat .env
  fi
}

function write_to_build_vars(){
  echo "export PROJECT_NAME=${PROJECT_NAME};" >> build-vars.sh
  echo "export PROJECT_VERSION=${PROJECT_VERSION};" >> build-vars.sh
  echo "export BUILD_IMAGE_NAME=${PROJECT_NAME};" >> build-vars.sh
  get_build_image_tag
  if [ "$DEBUG" == "true" ]; then
    cat build-vars.sh
  fi
}

function get_build_image_tag(){
  if [ -z ${CI_COMMIT_TAG+x} ]; then
    echo "export BUILD_IMAGE_TAG=${PROJECT_VERSION}.rc.${CI_PIPELINE_ID};" >> build-vars.sh
  else
    echo "export BUILD_IMAGE_TAG=${CI_COMMIT_TAG};" >> build-vars.sh
  fi
}

function job_image_push_template_check_variable(){
  job_image_push_template_check_old_image_name
  job_image_push_template_check_old_image_tag
  job_image_push_template_check_new_image_name
  job_image_push_template_check_new_image_tag
}

function job_image_push_template_check_old_image_name(){
  if [ -z ${OLD_IMAGE_NAME+x} ]; then
    export OLD_IMAGE_NAME=${BUILD_IMAGE_NAME}
  fi
}

function job_image_push_template_check_old_image_tag(){
  if [ -z ${OLD_IMAGE_TAG+x} ]; then
    export OLD_IMAGE_TAG=${BUILD_IMAGE_TAG}
  fi
}

function job_image_push_template_check_new_image_name(){
  if [ -z ${NEW_IMAGE_NAME+x} ]; then
    export NEW_IMAGE_NAME=${BUILD_IMAGE_NAME}
  fi
}

function job_image_push_template_check_new_image_tag(){
  if [ -z ${NEW_IMAGE_TAG+x} ]; then
    export NEW_IMAGE_TAG=${BUILD_IMAGE_TAG}
  fi
}

