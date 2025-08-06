####  系统版本太老,nodejs 无法安装

~~~
nvm install --lts
Installing latest LTS version.
Downloading and installing node v22.17.1...
Downloading https://nodejs.org/dist/v22.17.1/node-v22.17.1-linux-x64.tar.xz...
######################################################################## 100.0%
Computing checksum with sha256sum
Checksums matched!
manpath: can't set the locale; make sure $LC_* and $LANG are correct
Now using node v22.17.1
Creating default alias: default -> lts/* (-> v22.17.1)
[root@localhost ~]# npm -v
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.21' not found (required by node)
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.20' not found (required by node)
node: /lib64/libstdc++.so.6: version CXXABI_1.3.9' not found (required by node)
node: /lib64/libm.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.28' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.25' not found (required by node)
[root@localhost ~]# node -v
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.21' not found (required by node)
node: /lib64/libstdc++.so.6: version GLIBCXX_3.4.20' not found (required by node)
node: /lib64/libstdc++.so.6: version CXXABI_1.3.9' not found (required by node)
node: /lib64/libm.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.27' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.28' not found (required by node)
node: /lib64/libc.so.6: version GLIBC_2.25' not found (required by node)
~~~

升级系统或者使用docker 安装

docker pull node:18

docker run -it --rm node:18 bash
新建容器
docker exec -it <container_id> bash
使用既有容器

#### docker 服务未启动
~~~
docker pull node:18
Cannot connect to the Docker daemon at unix:///var/run/docker.sock. Is the docker daemon running?
 
~~~

systemctl start docker



#### docker 内部服务器端口外部无法看到,必须端口映射
docker run -it -p 8545:8545 -v $PWD:/app -w /app node:18 bash

#### docker 进入容器执行命令
docker ps
docker exec -it container_ID bash


#### deploy 方法不正确

~~~
root@70728ff479ea:/app# npx hardhat run scripts/deploy.js --network localhost
WARNING: You are currently using Node.js v18.20.8, which is not supported by Hardhat. This can lead to unexpected behavior. See https://hardhat.org/nodejs-versions


Downloading compiler 0.8.28
Compiled 2 Solidity files successfully (evm target: paris).
Deploying contracts with the account: 0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266
TypeError: greeter.deployed is not a function
    at main (/app/scripts/deploy.js:9:17)
    at processTicksAndRejections (node:internal/process/task_queues:95:5)
root@70728ff479ea:/app# cd scripts/
~~~

改为

~~~

  const Greeter = await ethers.getContractFactory("Greeter");
  const greeter = await Greeter.deploy("Hello, Hardhat!");

  await greeter.waitForDeployment();

~~~










