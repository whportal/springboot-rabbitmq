// github凭证
def git_auth = "github-ssh"
// 构建版本的名称
def tag = "latest"
// Harbor私服地址
def harbor_url = "192.168.10.252:85/rabbitmq"
node {


    stage('拉取代码') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: 'git@github.com:coder1874/springboot-rabbitmq.git']]])
    }

    stage('编译构建镜像') {
        // 定义镜像名称
        def imageName = "${project_name}:${tag}"

        // 编译安装公共工程
        // sh "mvn -f common clean install"

        // 编译构建本地镜像
        sh "mvn -f ${project_name} clean package -Dmaven.test.skip=true dockerfile:build"
    }

}