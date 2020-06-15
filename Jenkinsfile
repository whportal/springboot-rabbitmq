// github凭证
def git_auth = "github-ssh"
// 构建版本的名称
def tag = "latest"
// Harbor私服地址
def harbor_url = "192.168.10.252:85/rabbitmq"
// Harbor项目名称
def harbor_project_name = "rabbitmq"
// Harbor凭证
def harbor_auth = "eb7ed65a-75d7-44ca-be0c-1a4442a1bde5"
node {


    stage('拉取代码') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: 'git@github.com:coder1874/springboot-rabbitmq.git']]])
    }

    stage('编译构建镜像') {
        // 定义镜像名称
        def imageName = "${project_name}:${tag}"

		sh "echo ${imageName}"

        // 编译安装公共工程
		// sh "mvn clean install -Dmaven.test.skip=true -pl common -am"

        // 编译构建本地镜像 -pl --projects<arg>构建指定的模块 模块之间用逗号分隔  -am --also-make 同时构建所列模块的依赖模块
        sh "mvn -f ${project_name} clean package -Dmaven.test.skip=true dockerfile:build"

		// 给镜像打标签
		sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"

		// 登录Harbor并上传镜像
		withCredentials([usernamePassword(credentialsId: "${harbor_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {
			// 登录
			sh "docker login -u ${username} -p ${password} ${harbor_url}"

			// 上传镜像
			sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
		}

		// 删除本地镜像
		sh "docker rmi -f ${imageName}"
		sh "docker rmi -f ${harbor_url}/${harbor_project_name}/${imageName}"
    }

	stage("构建完成"){
		sh "echo 构建完成"
	}

}