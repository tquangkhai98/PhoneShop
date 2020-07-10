package com.capstone.phoneshop.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.phoneshop.Adapter.ImageSliderAdapter;
import com.capstone.phoneshop.Data.DowloaderProductDetail;
import com.capstone.phoneshop.Model.CartItem;
import com.capstone.phoneshop.Model.Color;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.R;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Downloader;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class ProductDetailActivity extends BaseActivity {

    private SliderView imageSliderView;
    private Button btSelectBuy;
    private int productID;
    public static Product productSelected;
    private List<Color> colorList;
    private List<String> listColorText;
    private List<String> listImageURL;
    private CartItem newItem;
    //private DowloaderProductDetail dowloader;

    public void initProductDetail(){
        //
            //tra chi tiet san pham = product_ID
        //
        /*productSelected = new Product(11,"Iphone 11 Pro Max",33990000,5,"https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-400x460.png",
                "Chapter One\n" +
                        "A Stop on the Salt Route\n" +
                        "1000 B.C.\n" +
                        "As they rounded a bend in the path that ran beside the river, Lara recognized the silhouette of a fig tree atop a nearby hill. The weather was hot and the days were long. The fig tree was in full leaf, but not yet bearing fruit.\n" +
                        "Soon Lara spotted other landmarks—an outcropping of limestone beside the path that had a silhouette like a man’s face, a marshy spot beside the river where the waterfowl were easily startled, a tall tree that looked like a man with his arms upraised. They were drawing near to the place where there was an island in the river. The island was a good spot to make camp. They would sleep on the island tonight.\n" +
                        "Lara had been back and forth along the river path many times in her short life. Her people had not created the path—it had always been there, like the river—but their deerskin-shod feet and the wooden wheels of their handcarts kept the path well worn. Lara’s people were salt traders, and their livelihood took them on a continual journey.\n" +
                        "At the mouth of the river, the little group of half a dozen intermingled families gathered salt from the great salt beds beside the sea. They groomed and sifted the salt and loaded it into handcarts. When the carts were full, most of the group would stay behind, taking shelter amid rocks and simple lean-tos, while a band of fifteen or so of the heartier members set out on the path that ran alongside the river.\n" +
                        "With their precious cargo of salt, the travelers crossed the coastal lowlands and traveled toward the mountains. But Lara’s people never reached the mountaintops; they traveled only as far as the foothills. Many people lived in the forests and grassy meadows of the foothills, gathered in small villages. In return for salt, these people would give Lara’s people dried meat, animal skins, cloth spun from wool, clay pots, needles and scraping tools carved from bone, and little toys made of wood.\n" +
                        "Their bartering done, Lara and her people would travel back down the river path to the sea. The cycle would begin again.\n" +
                        "It had always been like this. Lara knew no other life. She traveled back and forth, up and down the river path. No single place was home. She liked the seaside, where there was always fish to eat, and the gentle lapping of the waves lulled her to sleep at night. She was less fond of the foothills, where the path grew steep, the nights could be cold, and views of great distances made her dizzy. She felt uneasy in the villages, and was often shy around strangers. The path itself was where she felt most at home. She loved the smell of the river on a hot day, and the croaking of frogs at night. Vines grew amid the lush foliage along the river, with berries that were good to eat. Even on the hottest day, sundown brought a cool breeze off the water, which sighed and sang amid the reeds and tall grasses.\n" +
                        "Of all the places along the path, the area they were approaching, with the island in the river, was Lara’s favorite.\n" +
                        "The terrain along this stretch of the river was mostly flat, but in the immediate vicinity of the island, the land on the sunrise side was like a rumpled cloth, with hills and ridges and valleys. Among Lara’s people, there was a wooden baby’s crib, suitable for strapping to a cart, that had been passed down for generations. The island was shaped like that crib, longer than it was wide and pointed at the upriver end, where the flow had eroded both banks. The island was like a crib, and the group of hills on the sunrise side of the river were like old women mantled in heavy cloaks gathered to have a look at the baby in the crib—that was how Lara’s father had once described the lay of the land.\n" +
                        "Larth spoke like that all the time, conjuring images of giants and monsters in the landscape. He could perceive the spirits, called numina, that dwelled in rocks and trees. Sometimes he could speak to them and hear what they had to say. The river was his oldest friend and told him where the fishing would be best. From whispers in the wind he could foretell the next day’s weather. Because of such skills, Larth was the leader of the group.\n" +
                        "“We’re close to the island, aren’t we, Papa?” said Lara.\n" +
                        "“How did you know?”\n" +
                        "“The hills. First we start to see the hills, off to the right. The hills grow bigger. And just before we come to the island, we can see the silhouette of that fig tree up there, along the crest of that hill.”\n" +
                        "“Good girl!” said Larth, proud of his daughter’s memory and powers of observation. He was a strong, handsome man with flecks of gray in his black beard. His wife had borne several children, but all had died very young except Lara, the last, whom his wife had died bearing. Lara was very precious to him. Like her mother, she had golden hair. Now that she had reached the age of childbearing, Lara was beginning to display the fullness of a woman’s hips and breasts. It was Larth’s greatest wish that he might live to see his own grandchildren. Not every man lived that long, but Larth was hopeful. He had been healthy all his life, partly, he believed, because he had always been careful to show respect to the numina he encountered on his journeys.\n" +
                        "Respecting the numina was important. The numen of the river could suck a man under and drown him. The numen of a tree could trip a man with its roots, or drop a rotten branch on his head. Rocks could give way underfoot, chuckling with amusement at their own treachery. Even the sky, with a roar of fury, sometimes sent down fingers of fire that could roast a man like a rabbit on a spit, or worse, leave him alive but robbed of his senses. Larth had heard that the earth itself could open and swallow a man; though he had never actually seen such a thing, he nevertheless performed a ritual each morning, asking the earth’s permission before he went striding across it.\n" +
                        "“There’s something so special about this place,” said Lara, gazing at the sparkling river to her left and then at the rocky, tree-spotted hills ahead and to her right. “How was it made? Who made it?”"
                        ,
                1,1);*/
        productSelected = new Product();
        final Intent intent = getIntent();
        productID = intent.getIntExtra("product_ID",0);
        Toast.makeText(getApplicationContext(),"ProductID " + productID,Toast.LENGTH_LONG).show();
        try {

            new DowloaderProductDetail().execute(productID);

        }catch (Exception e){
            e.printStackTrace();
        }


        colorList = new ArrayList<>();

        /*colorList.add(new Color(1,"Xanh"));
        colorList.add(new Color(2,"Đỏ"));
        colorList.add(new Color(3,"Tím"));
        colorList.add(new Color(4,"Vàng"));
        colorList.add(new Color(5,"Hồng cánh sen"));*/

        colorList.add(new Color(1,"Đỏ"));
        colorList.add(new Color(2,"Hồng"));
        colorList.add(new Color(3,"Đen"));
        colorList.add(new Color(4,"Trắng"));
        colorList.add(new Color(5,"Bạc"));
        colorList.add(new Color(6,"Xám"));
        colorList.add(new Color(7,"Xanh lam"));

        listColorText = new ArrayList<>();

        for(int i = 0;i<colorList.size();i++){
            listColorText.add(colorList.get(i).getColorName());
        }

        listImageURL = new ArrayList<>();
        /*listImageURL.add("https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763926758_11-pro-max-xanh.png");
        listImageURL.add("https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687764284663_11-pro-max-den.png");
        listImageURL.add("https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763107876_11-pro-max-trang.png");
        listImageURL.add("https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687765081535_11-pro-max-vang.png");*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setTitle(R.string.titlte_product_detail);

        //dowloader = new DowloaderProductDetail();

        initProductDetail();

        final ViewHolder viewHolder = new ViewHolder(findViewById(android.R.id.content).getRootView());

        //Toast.makeText(getApplicationContext(),product_ID+"",Toast.LENGTH_SHORT).show();

        imageSliderView = findViewById(R.id.imageSliderView);
        imageSliderView.setSliderAdapter(new ImageSliderAdapter(this,listImageURL));
        imageSliderView.setAutoCycle(false);
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_LONG).show();
        new CountDownTimer(1500,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                viewHolder.textProductName.setText(productSelected.getName());
                //viewHolder.textPrice.setText(String.format("%.0f",productSelected.getPrice())+" đ");
                viewHolder.textPrice.setText(NumberFormat.getInstance().format(productSelected.getPrice())+" đ");
                viewHolder.textDescripton.setText(productSelected.getDescription());
                for(int i = 0;i<4;i++){
                    listImageURL.add(productSelected.getImageURL());
                }
                imageSliderView = findViewById(R.id.imageSliderView);
                imageSliderView.setSliderAdapter(new ImageSliderAdapter(ProductDetailActivity.this,listImageURL));
                imageSliderView.setAutoCycle(false);
            }
        }.start();


        /*viewHolder.textProductName.setText(productSelected.getName());
        //viewHolder.textPrice.setText(String.format("%.0f",productSelected.getPrice())+" đ");
        viewHolder.textPrice.setText(NumberFormat.getInstance().format(productSelected.getPrice())+" đ");
        viewHolder.textDescripton.setText(productSelected.getDescription());*/
        //Log.i("Product Name",productSelected.getName());
        viewHolder.spinnerColor.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,listColorText));

        btSelectBuy = findViewById(R.id.bt_select_buy);
        btSelectBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String optionColor = viewHolder.spinnerColor.getSelectedItem().toString();
                Log.i("Color",optionColor);
                //them san pham vo gio hang  moi lan click là thêm 1 vào số lượng

                newItem = new CartItem(productSelected.getProductID(),productSelected.getName()+" - "+optionColor,productSelected.getPrice(),productSelected.getImageURL(),1);
                Log.i("imageProduct",productSelected.getImageURL());
                if(MainActivity.db.checkCartContains(newItem)){
                    Toast.makeText(getApplicationContext(),R.string.cart_cotains_this_item,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.select_buy,Toast.LENGTH_SHORT).show();
                    MainActivity.db.addCartItem(newItem);
                }

                //MainActivity.itemList.add(newItem);

                /*if(MainActivity.indexOf(newItem)==-1) {
                    Toast.makeText(getApplicationContext(),R.string.select_buy,Toast.LENGTH_SHORT).show();
                    MainActivity.itemList.add(newItem);
                }else{
                    Toast.makeText(getApplicationContext(),R.string.cart_cotains_this_item,Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    public class ViewHolder{
        public TextView textProductName;
        public TextView textPrice;
        public Spinner spinnerColor;
        public Button buttonBuy;
        public TextView textDescripton;

        public ViewHolder(View view){
            textProductName = view.findViewById(R.id.tv_product_name);
            textPrice = view.findViewById(R.id.tv_price_name);
            spinnerColor = view.findViewById(R.id.spinner_color);
            buttonBuy = view.findViewById(R.id.bt_select_buy);
            textDescripton = view.findViewById(R.id.tv_description);
        }

    }
}
