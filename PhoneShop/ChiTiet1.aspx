﻿<%@ Page Title="" Language="C#" MasterPageFile="~/ChiTietMaster.Master" AutoEventWireup="true" CodeBehind="ChiTiet1.aspx.cs" Inherits="PhoneShop.ChiTiet1" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="main">
        <div class="content">
            <div class="content_top">
                <div class="back-links">
                    <p><a href="index.html">Home</a> >>>> <a href="#">Electronics</a></p>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
                <div class="section group">
                    <div class="cont-desc span_1_of_2">
                        <div class="product-details">
                            <asp:Repeater ID="Repeater1" runat="server">
                                <ItemTemplate>

                            <div class="grid images_3_of_2">
                                <div id="container">
                                    <div id="products_example">
                                        <div id="products">
                                            <div class="slides_container">
                                                <a href="#" target="_blank">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" /></a>
                                                <a href="#" target="_blank">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" /></a>
                                                <a href="#" target="_blank">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" /></a>
                                                <a href="#" target="_blank">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" /></a>
                                                <a href="#" target="_blank">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" /></a>
                                                <a href="#" target="_blank">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" /></a>
                                            </div>
                                            <ul class="pagination">
                                                <li><a href="#">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" width="25px" height="30px" /></a></li>
                                                <li><a href="#">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" width="25px" height="30px" /></a></li>
                                                <li><a href="#">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" width="25px" height="30px" /></a></li>
                                                <li><a href="#">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" width="25px" height="30px" /></a></li>
                                                <li><a href="#">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" width="25px" height="30px" /></a></li>
                                                <li><a href="#">
                                                    <img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>" width="25px" height="30px" /></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="desc span_3_of_2">
                                <h2><%#Eval("TenSanPham") %></h2>
                                <p><%#Eval("MoTa") %></p>
                                <div class="price">
                                    <p>Price: <span><%#Eval("DonGia") %></span></p>
                                </div>
                                </div>
                               </ItemTemplate>
                                </asp:Repeater>    
                                <div class="desc span_3_of_2">
                                <div class="share-desc">
                                    <div class="available">
                                    <p>Available Options :</p>
                                        <ul>
                                            <li>Số lượng :&nbsp; 
                                                 <asp:DropDownList ID="drop_SOLUONG" runat="server"></asp:DropDownList>
                                            </li>
                                        </ul>
                                        
                                    <!--<div class="available">
                                    <p>Available Options :</p>
                                    <ul>
                                        <!--  <li>
                                              Color:
                                              <select>
                                                  <option>Silver</option>
                                                  <option>Black</option>
                                                  <option>Dark Black</option>
                                                  <option>Red</option>
                                              </select>
                                          </li>
                                          <li>
                                              Size:<select>
                                                  <option>Large</option>
                                                  <option>Medium</option>
                                                  <option>small</option>
                                                  <option>Large</option>
                                                  <option>small</option>
                                              </select>
                                          </li>
                                        <li>
                                            Số lượng :
                                            @Html.TextBox("txt_soluong");
                                        </li>

                                    </ul>
                                </div>-->


                                    <div class="add-cart">
                                        <h4>
                                            <asp:Button ID="Button1" runat="server" Text="Add to Cart" OnClick="Button1_Click" style="font-size: 0.9em; display: block; padding: 10px 15px; background: #CD1F25; color: #FFF; text-decoration: none; outline: 0;" />
                                            <asp:Button ID="Button2" runat="server" Text="Button" OnClick="Button2_Click" />    
                                        </h4>
                                    </div>

                                    <div class="clear"></div>
                                </div>
                            </div>
                            <div class="clear"></div>
                                    
                            
                        </div>
                        <div class="product_desc">
                            <div id="horizontalTab">
                                <ul class="resp-tabs-list">
                                    <li>Product Details</li>
                                    <li>Product Reviews</li>
                                    <div class="clear"></div>
                                </ul>
                                <div class="resp-tabs-container">
                                    <div class="product-desc">
                                        <p><strong>Đặc điểm nổi bật của iPhone Xs Max 256GB</strong></p>
                                        <p>Sau 1 năm mong chờ, chiếc smartphone cao cấp nhất của Apple đã chính thức ra mắt mang tên iPhone Xs Max. Máy các trang bị các tính năng cao cấp nhất từ chip A12 Bionic, dàn loa đa chiều cho tới camera kép tích hợp trí tuệ nhân tạo.</p>
                                        <p>
                                            Hiệu năng đỉnh cao cùng chip Apple A12<br />
                                            iPhone Xs Max được Apple trang bị cho con chip mới toanh hàng đầu của hãng mang tên Apple A12</br>Chip A12 Bionic được xây dựng trên tiến trình 7nm đầu tiên mà hãng sản xuất với 6 nhân đáp ứng vượt trội trong việc xử lý các tác vụ và khả năng tiết kiệm năng lượng tối ưu.<br />
                                        </p>
                                        <p>
                                            <img src="~/HinhAnhSanPham/AnhMoTa/iphone-xs-max-256gb-gold-6.jpg" />
                                        </p>
                                    </div>
                                    <div class="review">
                                        <h4>Lorem ipsum Review by <a href="#">Finibus Bonorum</a></h4>
                                        <ul>
                                            <li>Price :<a href="#"><img src="/Asserts/NguoiDung//images/price-rating.png" alt="" /></a></li>
                                            <li>Value :<a href="#"><img src="/Asserts/NguoiDung/images/value-rating.png" alt="" /></a></li>
                                            <li>Quality :<a href="#"><img src="/Asserts/NguoiDung/images/quality-rating.png" alt="" /></a></li>
                                        </ul>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
                                        <div class="your-review">
                                            <h3>How Do You Rate This Product?</h3>
                                            <p>Write Your Own Review?</p>
                                            <div class="box">
                                                <div>
                                                    <span>
                                                        <label>Nickname<span class="red">*</span></label></span>
                                                    <span>
                                                        <input type="text" value=""></span>
                                                </div>
                                                <div>
                                                    <span>
                                                        <label>Summary of Your Review<span class="red">*</span></label></span>
                                                    <span>
                                                        <input type="text" value=""></span>
                                                </div>
                                                <div>
                                                    <span>
                                                        <label>Review<span class="red">*</span></label></span>
                                                    <span>
                                                        <textarea> </textarea></span>
                                                </div>
                                                <div>
                                                    <span>
                                                        <input type="submit" value="SUBMIT REVIEW"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                               
                        <script type="text/javascript">
                            $(document).ready(function () {
                                $('#horizontalTab').easyResponsiveTabs({
                                    type: 'default', //Types: default, vertical, accordion
                                    width: 'auto', //auto or any width like 600px
                                    fit: true   // 100% fit in a container
                                });
                            });
                        </script>
                        <div class="content_bottom">
                            <div class="heading">
                                <h3>Related Products</h3>
                            </div>
                            <div class="see">
                                <p><a href="#">See all Products</a></p>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <asp:DataList ID="DataList2" runat="server" RepeatColumns="3">
                            <ItemTemplate>
                        <div class="section group">
                            <div class="grid_1_of_4 images_1_of_4">
                                <a href="#"><img src="<%# "/images/"+Eval("HinhAnh") %>" alt="<%#Eval("TenSanPham") %>"></a>
                                <h6><%#Eval("TenSanPham") %></h6>
                                <div class="price-details">
                                    <div class="price-number">
                                        <p><span class="rupees"><%#Eval("DonGia") %></span></p>
                                    </div>
                                        <div class="add-cart">
                                            <h4><input type="submit" value="Add to Cart" style="font-size: 0.9em; display: block; padding: 10px 15px;background: #CD1F25;color: #FFF;text-decoration: none;outline: 0;" /></h4>
                                        </div>
                                    <div class="clear"></div>
                                </div>

                            </div>
                        </div>
                            </ItemTemplate>
                        </asp:DataList>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
</asp:Content>
