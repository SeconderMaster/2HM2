package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

public class Add_My_ShippingAddress_City extends AppCompatActivity {

    private TextView ic_tb_add_my_shipping_address_city_back;
    private RecyclerView add_my_shipping_address_city_recyclerView;
    private TextView province;
    private String re_province;
    static ArrayList<String> cityArrayList;
    private AddShippingAdapter addShippingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__my__shipping_address__city);
        initView();
        add_my_shipping_address_city_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add_my_shipping_address_city_recyclerView.setAdapter(addShippingAdapter =new AddShippingAdapter(this,cityArrayList));
        addShippingAdapter.setOnItemClickListener(new AddShippingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(Edit_Data.isAddress==true){
                    String address=re_province+cityArrayList.get(position).toString();
                    UpdateAddress updateAddress=new UpdateAddress(Add_My_ShippingAddress_City.this,address);
                    Edit_Data.isAddress=false;
                    startActivity(new Intent(Add_My_ShippingAddress_City.this,Edit_Data.class));
                    Add_My_ShippingAddress_City.this.finish();
                    Add_My_ShippingAddress_Province.instance.finish();

                }
                else {
                    Intent i=new Intent(Add_My_ShippingAddress_City.this,Add_My_ShippingAddress_Other.class);
                    i.putExtra("add_my_shipping_address_province",re_province);
                    i.putExtra("add_my_shipping_address_city",cityArrayList.get(position));
                    startActivity(i);
                }
            }
        });
    }

    private void initView() {
        ic_tb_add_my_shipping_address_city_back= (TextView) findViewById(R.id.ic_tb_add_my_shipping_address_city_back);
        province= (TextView) findViewById(R.id.province);
        add_my_shipping_address_city_recyclerView= (RecyclerView) findViewById(R.id.add_my_shipping_address_city_recyclerView);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_add_my_shipping_address_city_back.setTypeface(iconfont1);

        Intent i=getIntent();
        re_province=i.getStringExtra("add_my_shipping_address_province");
        province.setText(re_province);

        initCityArrayList();
    }

    private void initCityArrayList() {
        cityArrayList=new ArrayList<String>();
        switch (province.getText().toString()){
            case "北京":
                cityArrayList.add("北京市");
                break;
            case "天津":
                cityArrayList.add("天津市");
                break;
            case "上海":
                cityArrayList.add("上海市");
                break;
            case "重庆":
                cityArrayList.add("重庆市");
                break;
            case "河北省":
                cityArrayList.add("石家庄市");cityArrayList.add("保定市");cityArrayList.add("秦皇岛");cityArrayList.add("唐山市");cityArrayList.add("邯郸市");
                cityArrayList.add("邢台市");cityArrayList.add("沧州市");cityArrayList.add("承德市");cityArrayList.add("廊坊市");cityArrayList.add("衡水市");
                cityArrayList.add("张家口市");
                break;
            case "河南省":
                cityArrayList.add("郑州市");cityArrayList.add("洛阳市");cityArrayList.add("焦作市");cityArrayList.add("商丘市");cityArrayList.add("信阳市");
                cityArrayList.add("新乡市");cityArrayList.add("安阳市");cityArrayList.add("开封市");cityArrayList.add("漯河市");cityArrayList.add("南阳市");
                cityArrayList.add("鹤壁市");cityArrayList.add("平顶山");cityArrayList.add("濮阳市");cityArrayList.add("许昌市");cityArrayList.add("周口市");
                cityArrayList.add("三门峡");cityArrayList.add("驻马店");cityArrayList.add("安阳市");
                break;
            case "云南省":
                cityArrayList.add("昆明市");cityArrayList.add("玉溪市");cityArrayList.add("大理市");cityArrayList.add("曲靖市");cityArrayList.add("昭通市");
                cityArrayList.add("保山市");cityArrayList.add("丽江市");cityArrayList.add("临沧市");
                break;
            case "辽宁省":
                cityArrayList.add("沈阳市");cityArrayList.add("大连市");cityArrayList.add("鞍山市");cityArrayList.add("抚顺市");cityArrayList.add("本溪市");
                cityArrayList.add("丹东市");cityArrayList.add("锦州市");cityArrayList.add("营口市");cityArrayList.add("阜新市");cityArrayList.add("辽阳市");
                cityArrayList.add("盘锦市");cityArrayList.add("铁岭市");cityArrayList.add("朝阳市");cityArrayList.add("葫芦岛市");
                break;
            case "黑龙江省":
                cityArrayList.add("哈尔滨市");cityArrayList.add("大兴安岭市");cityArrayList.add("鸡西市");cityArrayList.add("鹤岗市");
                cityArrayList.add("绥化市");cityArrayList.add("双鸭山");cityArrayList.add("七台河");cityArrayList.add("佳木斯");cityArrayList.add("黑河市");
                cityArrayList.add("齐齐哈尔市");cityArrayList.add("伊春市");cityArrayList.add("牡丹江");cityArrayList.add("大庆市");
                break;
            case "湖南省":
                cityArrayList.add("长沙市");cityArrayList.add("郴州市");cityArrayList.add("娄底市");cityArrayList.add("衡阳市");cityArrayList.add("株洲市");
                cityArrayList.add("湘潭市");cityArrayList.add("岳阳市");cityArrayList.add("常德市");cityArrayList.add("邵阳市");cityArrayList.add("益阳市");
                cityArrayList.add("永州市");cityArrayList.add("张家界");cityArrayList.add("怀化市");cityArrayList.add("湘西土家族苗族自治州");
                break;
            case "安徽省":
                cityArrayList.add("合肥市");cityArrayList.add("芜湖市");cityArrayList.add("亳州市");cityArrayList.add("马鞍山");cityArrayList.add("池州市");
                cityArrayList.add("淮南市");cityArrayList.add("淮北市");cityArrayList.add("蚌埠市");cityArrayList.add("巢湖市");cityArrayList.add("安庆市");
                cityArrayList.add("宿州市");cityArrayList.add("宣城市");cityArrayList.add("滁州市");cityArrayList.add("黄山市");cityArrayList.add("六安市");
                cityArrayList.add("阜阳市");cityArrayList.add("铜陵市");
                break;
            case "山东省":
                cityArrayList.add("济南市");cityArrayList.add("青岛市");cityArrayList.add("威海市");cityArrayList.add("枣庄市");cityArrayList.add("泰安市");
                cityArrayList.add("临沂市");cityArrayList.add("东营市");cityArrayList.add("济宁市");cityArrayList.add("烟台市");cityArrayList.add("菏泽市");
                cityArrayList.add("日照市");cityArrayList.add("德州市");cityArrayList.add("聊城市");cityArrayList.add("滨州市");cityArrayList.add("莱芜市");
                cityArrayList.add("潍坊市");cityArrayList.add("淄博市");
                break;
            case "新疆维吾尔":
                cityArrayList.add("乌鲁木齐");cityArrayList.add("克拉玛依市");cityArrayList.add("吐鲁番市");cityArrayList.add("哈密地区");
                break;
            case "江苏省":
                cityArrayList.add("南京市");cityArrayList.add("无锡市");cityArrayList.add("常州市");cityArrayList.add("扬州市");cityArrayList.add("徐州市");
                cityArrayList.add("苏州市");cityArrayList.add("连云港");cityArrayList.add("盐城市");cityArrayList.add("淮安市");cityArrayList.add("宿迁市");
                cityArrayList.add("镇江市");cityArrayList.add("南通市");cityArrayList.add("泰州市");
                break;
            case "浙江省":
                cityArrayList.add("杭州市");cityArrayList.add("宁波市");
                cityArrayList.add("绍兴市");cityArrayList.add("温州市");cityArrayList.add("湖州市");cityArrayList.add("嘉兴市");cityArrayList.add("台州市");
                cityArrayList.add("金华市");cityArrayList.add("舟山市");cityArrayList.add("衢州市");cityArrayList.add("丽水市");
                break;
            case "江西省":
                cityArrayList.add("南昌市");cityArrayList.add("赣州市");cityArrayList.add("景德镇");cityArrayList.add("九江市");cityArrayList.add("萍乡市");
                cityArrayList.add("新余市");cityArrayList.add("抚州市");cityArrayList.add("宜春市");cityArrayList.add("上饶市");cityArrayList.add("鹰潭市");
                cityArrayList.add("吉安市");
                break;
            case "湖北省":
                cityArrayList.add("武汉市");cityArrayList.add("黄石市");cityArrayList.add("十堰市");cityArrayList.add("宜昌市");cityArrayList.add("襄阳市");
                cityArrayList.add("鄂州市");cityArrayList.add("荆门市");cityArrayList.add("孝感市");cityArrayList.add("荆州市");cityArrayList.add("黄冈市");
                cityArrayList.add("咸宁市");cityArrayList.add("随州市");cityArrayList.add("恩施土家族苗族自治州");cityArrayList.add("仙桃市");cityArrayList.add("潜江市");
                cityArrayList.add("天门市");cityArrayList.add("神农架林区");
                break;
            case "广西壮族":
                cityArrayList.add("南宁市");cityArrayList.add("贺州市");cityArrayList.add("柳州市");cityArrayList.add("桂林市");cityArrayList.add("梧州市");
                cityArrayList.add("北海市");cityArrayList.add("玉林市");cityArrayList.add("钦州市");cityArrayList.add("百色市");cityArrayList.add("防城港");
                cityArrayList.add("贵港市");cityArrayList.add("河池市");cityArrayList.add("崇左市");cityArrayList.add("来宾市");
                break;
            case "甘肃省":
                cityArrayList.add("兰州市");cityArrayList.add("白银市");cityArrayList.add("武威市");cityArrayList.add("金昌市");cityArrayList.add("平凉市");
                cityArrayList.add("张掖市");cityArrayList.add("嘉峪关");cityArrayList.add("酒泉市");cityArrayList.add("庆阳市");cityArrayList.add("定西市");
                cityArrayList.add("陇南市");cityArrayList.add("天水市");
                break;
            case "山西省":
                cityArrayList.add("太原市");cityArrayList.add("大同市");cityArrayList.add("阳泉市");cityArrayList.add("长治市");cityArrayList.add("临汾市");
                cityArrayList.add("晋中市");cityArrayList.add("运城市");cityArrayList.add("晋城市");cityArrayList.add("忻州市");cityArrayList.add("朔州市");
                cityArrayList.add("吕梁市");
                break;
            case "内蒙古":
                cityArrayList.add("呼和浩特市");cityArrayList.add("呼伦贝尔市");cityArrayList.add("包头市");cityArrayList.add("赤峰市");cityArrayList.add("乌海市");
                cityArrayList.add("通辽市");cityArrayList.add("鄂尔多斯市");cityArrayList.add("乌兰察布市");cityArrayList.add("巴彦淖尔市");
                cityArrayList.add("兴安盟");cityArrayList.add("锡林郭勒盟");cityArrayList.add("阿拉善盟");
                break;
            case "陕西省":
                cityArrayList.add("西安市");cityArrayList.add("咸阳市");cityArrayList.add("榆林市");cityArrayList.add("宝鸡市");cityArrayList.add("铜川市");cityArrayList.add("渭南市");
                cityArrayList.add("汉中市");cityArrayList.add("安康市");cityArrayList.add("商洛市");cityArrayList.add("延安市");
                break;
            case "吉林省":
                cityArrayList.add("吉林市");cityArrayList.add("通化市");cityArrayList.add("白城市");cityArrayList.add("四平市");cityArrayList.add("辽源市");
                cityArrayList.add("松原市");cityArrayList.add("白山市");cityArrayList.add("长春市");cityArrayList.add("延边朝鲜族自治州");
                break;
            case "福建省":
                cityArrayList.add("福州市");cityArrayList.add("厦门市");cityArrayList.add("漳州市");cityArrayList.add("南平市");cityArrayList.add("三明市");
                cityArrayList.add("龙岩市");cityArrayList.add("莆田市");cityArrayList.add("宁德市");cityArrayList.add("泉州市");
                break;
            case "贵州省":
                cityArrayList.add("贵阳市");cityArrayList.add("安顺市");cityArrayList.add("遵义市");cityArrayList.add("六盘水");
                cityArrayList.add("铜仁市");cityArrayList.add("毕节市");
                break;
            case "广东省":
                cityArrayList.add("广州市");cityArrayList.add("深圳市");cityArrayList.add("梅州市");cityArrayList.add("东沙群岛");
                cityArrayList.add("江门市");cityArrayList.add("佛山市");cityArrayList.add("汕头市");cityArrayList.add("湛江市");cityArrayList.add("韶关市");
                cityArrayList.add("中山市");cityArrayList.add("珠海市");cityArrayList.add("茂名市");cityArrayList.add("肇庆市");cityArrayList.add("阳江市");
                cityArrayList.add("惠州市");cityArrayList.add("潮州市");cityArrayList.add("揭阳市");cityArrayList.add("清远市");cityArrayList.add("河源市");
                cityArrayList.add("东莞市");cityArrayList.add("汕尾市");cityArrayList.add("云浮市");
                break;
            case "青海省":
                cityArrayList.add("西宁市");cityArrayList.add("海东市");
                break;
            case "西藏":
                cityArrayList.add("拉萨市");cityArrayList.add("昌都市");cityArrayList.add("山南地区");cityArrayList.add("日喀则市");
                cityArrayList.add("那曲地市");cityArrayList.add("阿里地区");cityArrayList.add("林芝市");
                break;
            case "四川省":
                cityArrayList.add("成都市");cityArrayList.add("达州市");
                cityArrayList.add("乐山市");cityArrayList.add("雅安市");cityArrayList.add("广安市");cityArrayList.add("南充市");cityArrayList.add("自贡市");
                cityArrayList.add("泸州市");cityArrayList.add("内江市");cityArrayList.add("宜宾市");cityArrayList.add("广元市");cityArrayList.add("资阳市");
                cityArrayList.add("绵阳市");cityArrayList.add("眉山市");cityArrayList.add("巴中市");cityArrayList.add("攀枝花");cityArrayList.add("遂宁市");
                cityArrayList.add("德阳市");cityArrayList.add("阿贝藏族羌族自治州");cityArrayList.add("甘孜藏族自治州");cityArrayList.add("凉山彝族自治州");
                break;
            case "宁夏回族":
                cityArrayList.add("银川市");cityArrayList.add("固原市");cityArrayList.add("青铜峡市");cityArrayList.add("石嘴山市");cityArrayList.add("中卫市");
                break;
            case "海南省":
                cityArrayList.add("海口市");cityArrayList.add("三亚市");
                break;
            case "台湾省":
                cityArrayList.add("台北市");cityArrayList.add("高雄市");cityArrayList.add("台南市");cityArrayList.add("台中市");cityArrayList.add("金门县");
                break;
            case "香港特别行政区":
                cityArrayList.add("香港岛");cityArrayList.add("九龙");cityArrayList.add("新界");
                break;
            case "澳门特别行政区":
                cityArrayList.add("澳门半岛");cityArrayList.add("离岛");
                break;
        }
    }
}
