<?php

$code = $_GET["code"];
$state = $_GET["state"];
//echo "state=====:$state";
$urltest="http://192.168.1.121:8080/xwyy/front/wenxinlogin";
$url220="http://59.173.12.220:8080/xw/front/wenxinlogin";
$urlredr=$urltest;
if($state=="url220"){
	$urlredr=$url220;
}

$appid = "wx3f0e53cf74d792e6";
$appsecret = "efd485d86120bd58de1c4f7e2197e284";
$url1 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=$appid&secret=$appsecret&code=$code&grant_type=authorization_code";
//请求
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,$url1);
curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
$result = curl_exec($ch);
$json=json_decode($result);
//请求结束
$token = $json->access_token;
$openid = $json->openid;
/*echo "token=====:$token";
echo "\n";
echo "openid=====:$openid";
echo "\n";*/
$url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=$token&openid=$openid&lang=zh_CN";
//请求
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,$url2);
curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
$result2 = curl_exec($ch);
/*echo $result2;
echo "\n";*/
$json2=json_decode($result2);
$openid2 = $json2->openid;
$nickname = $json2->nickname;
$sex = $json2->sex;
//echo "urlredr=====:$urlredr";
Header("Location:$urlredr?nickname=$nickname&openid=$openid&sex=$sex");
//请求结束