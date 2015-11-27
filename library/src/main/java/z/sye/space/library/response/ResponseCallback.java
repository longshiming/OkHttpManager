package z.sye.space.library.response;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Syehunter on 2015/11/27.
 */
public abstract class ResponseCallBack<T> {

    public Type mType;

    public ResponseCallBack(){
        mType = getSuperClassType(getClass());
    }

    /**
     * 规范泛型参数
     * @param clazz
     * @return
     */
    static Type getSuperClassType(Class<?> clazz){
        Type superClass = clazz.getGenericSuperclass();
        if (superClass instanceof Class){
            throw new RuntimeException("Missing type parameter.");
        }
        //获取泛型
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    /**
     * 发送请求之前的操作
     */
    public void onPreExcute(){
    }

    /**
     * 发送请求之后的操作
     */
    public void onPostExcute(){
    }

    /**
     * 请求过程中
     * @param progress
     */
    public void onProgressUpdate(int progress){
    }

    public abstract void onResponse(T response);

    public abstract void onFailure(Request request, Exception e);

    public static final ResponseCallBack<String> defaultCallBack = new ResponseCallBack<String>() {

        @Override
        public void onResponse(String response) {

        }

        @Override
        public void onFailure(Request request, Exception e) {

        }
    };
}
