package com.cai.yi.mylibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.authreal.api.AuthBuilder;
import com.authreal.api.OnResultListener;
import com.authreal.component.AuthComponentFactory;
import com.face.bsdk.crypt.Md5;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * 获取AuthBuilder。
     * 请在每次调用前获取新的AuthBuilder
     * 一个AuthBuilder 不要调用两次start()方法
     * @return
     */
    private AuthBuilder getAuthBuilder() {

        // 订单号商户自己生成：不超过36位，非空，不能重复
        String partner_order_id = "201708249158888603" ;
        //商户pub_key ： 开户时通过邮件发送给商户
        String pubKey = "96ff1b27-e624-4616-b7db-11fe764a8897";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        //签名时间：有效期5分钟，请每次重新生成 :签名时间格式：yyyyMMddHHmmss
        String sign_time = simpleDateFormat.format(new Date());
        // 商户 security_key  ：  开户时通过邮件发送给商户
        String security_key = "29bca00c-5bc3-4ead-a33f-66b43b4265e5";
        // 签名规则
        String singStr = "pub_key=" + pubKey + "|partner_order_id=" + partner_order_id + "|sign_time=" + sign_time + "|security_key=" + security_key;
        //生成 签名
        String sign = Md5.encrypt(singStr);
        /** 以上签名 请在服务端生成，防止key泄露 */

        AuthBuilder authBuilder = new AuthBuilder(partner_order_id, pubKey, sign_time, sign, new OnResultListener() {
            @Override
            public void onResult(int op_type, String result) {
                Log.e("MainActivity:result", op_type+"//"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    /***
                     * 业务处理成功（不是认证成功）
                     */
                    if (jsonObject.has("success") && jsonObject.getString("success").equals("true")) {
                        /** 业务处理成功 ，可以根据不同的模块 处理数据 */
                        switch (op_type) {
                            case AuthBuilder.OPTION_EOORO:
                                //// TODO:  error
                                break;
                            case AuthBuilder.OPTION_OCR:
                                //// TODO:  OCR扫描 回调
                                break;
                            case AuthBuilder.OPTION_VERIFY:
                                //// TODO:  实名验证 回调
                                break;
                            case AuthBuilder.OPTION_LIVENESS:
                                //// TODO:  活体 回调
                                break;
                            case AuthBuilder.OPTION_COMPARE:
                                //// TODO:  人像比对 回调
                                break;
                            case AuthBuilder.OPTION_VIDEO:
                                //// TODO:  视频存证 回调
                                break;
                        }
                    }else {
                        /***
                         * 业务处理失败
                         */
                        String message = jsonObject.getString("message");
                        String errorcode = jsonObject.getString("errorcode");
                        /** 打印错误日志，可根据文档定位问题 */
                        Log.d("MainActivity", errorcode+":"+ message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

        return authBuilder;
    }

    public void open(View view) {
        /** 获取AuthBuilder对象 请每次开始流程获取最新对象 */
        getAuthBuilder()
                /** 添加 身份证ocr识别 模块
                 *
                 *  非必须添加
                 *  是否需要添加改模块，根据自己业务需要来判断
                 *  执行顺序和添加顺序一致。如有疑问，请联系有盾对接人员
                 *
                 * */
//                .addFollow(AuthComponentFactory.getOcrComponenet()
//                                /**设置展示确认页面 ： 非必需 */
//                                .showConfirm(true)
//                                /**设置展示确认页面 ： 非必需 */
//                                .mosaicIdName(false)
//                                /**设置展示确认页面 ： 非必需 */
//                                .mosaicIdNumber(false)
//                                /**设置异步通知地址 ： 非必需 */
//                                .setNotifyUrl("http:......")
//                        //更多设置项目参见文档：http://static.udcredit.com/doc/idsafe/android/V43/index.html
//                )

                /** 添加 实名验证 模块
                 *
                 *  非必须添加
                 *  是否需要添加改模块，根据自己业务需要来判断
                 *  执行顺序和添加顺序一致。如有疑问，请联系有盾对接人员
                 *
                 * */
//                .addFollow(AuthComponentFactory.getVerifyComponent()
//                        /** true:人像验证(可作为比对源参与比对) false:简项验证 */
//                        .needGridPhoto(true)
//                        /**设置异步通知地址 ： 非必需 */
//                        .setNotifyUrl("http:......"))

                /** 添加 活体检测 模块
                 *
                 *  非必须添加
                 *  是否需要添加改模块，根据自己业务需要来判断
                 *  执行顺序和添加顺序一致。如有疑问，请联系有盾对接人员
                 *
                 * */
                .addFollow(AuthComponentFactory.getLivingComponent()
                                /** 声音开关 */
                                .setVoiceEnable(false)
                                /**设置异步通知地址 ： 非必需 */
                              //  .setNotifyUrl("http:......")
                        //更多设置项目参见文档：http://static.udcredit.com/doc/idsafe/android/V43/index.html
                )

                /** 添加 人脸比对
                 *
                 *  非必须添加
                 *  是否需要添加改模块，根据自己业务需要来判断
                 *  执行顺序和添加顺序一致。如有疑问，请联系有盾对接人员
                 *
                 * */
//                .addFollow(AuthComponentFactory.getCompareComponent()
//                        /** 此示例比对项A 为 身份证人像面照片的人像抠图
//                         *  可以根据自己业务来更改，如有以为请联系有盾对接人员
//                         * */
//                        .setCompareItemA(CompareItemFactory.getCompareItemBySessioId(CompareItemSession.SessionType.PHOTO_IDENTIFICATION))
//                        /** 此示例比对项A 为 活体动作过程截图
//                         *  可以根据自己业务来更改，如有以为请联系有盾对接人员
//                         * */
//                        .setCompareItemB(CompareItemFactory.getCompareItemBySessioId(CompareItemSession.SessionType.PHOTO_LIVING))
//                        /**设置异步通知地址 ： 非必需 */
//                        .setNotifyUrl("http:......"))
                /** 开始流程
                 * 请传入 Activity 对象
                 * */
                .start(MainActivity.this);
    }
}
