using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;
using System.Data;

namespace TUVAN
{
   
   
    public struct GainRatio
    {
        public double value;
        public Attribute at;

        public GainRatio(double value1, Attribute a)
        {
            value = value1;
            at = a;
        }


    }

    public class Attribute
    {
        ArrayList mValues;
        string mName;
        object mLabel;
        public string mlable;
       public String tb_Name;

        /// <summary>
        /// Inicializa uma nova instância de uma classe Atribute
        /// </summary>
        /// <param name="name">Indica o nome do atributo</param>
        /// <param name="values">Indica os valores possíveis para o atributo</param>
        public Attribute(string name, string[] values, String lb,String tb1)
        {
            mName = name;
            mValues = new ArrayList(values);
            mValues.Sort();
            mlable = lb;
            this.tb_Name = tb1;
        }



        public Attribute(object Label)
        {
            mLabel = Label;
            mName = string.Empty;
            mValues = null;
        }

        /// <summary>
        /// Indica o nome do atributo
        /// </summary>
        public string AttributeName
        {
            get
            {
                return mName;
            }
        }

        public string AttributeLable
        {
            get
            {
                return this.mlable;
            }
        }

        /// <summary>
        /// Retorna um array com os valores do atributo
        /// </summary>
        public string[] values
        {
            get
            {
                if (mValues != null)
                    return (string[])mValues.ToArray(typeof(string));
                else
                    return null;
            }
        }

        /// <summary>
        /// Indica se um valor é permitido para este atributo
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public bool isValidValue(string value)
        {
            return indexValue(value) >= 0;
        }

        /// <summary>
        /// Retorna o índice de um valor
        /// </summary>
        /// <param name="value">Valor a ser retornado</param>
        /// <returns>O valor do índice na qual a posição do valor se encontra</returns>
        public int indexValue(string value)
        {
            if (mValues != null)
                return mValues.BinarySearch(value);
            else
                return -1;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            if (mlable != string.Empty)
            {
                return mlable;
            }
            else
            {
                return mlable.ToString();
            }
        }
    }
    class thuattoanC4
    {
       //TreeView Treec45;
        string SQL = null;
        DataTable tb_Mau;
        Attribute[] attributes;
       // TreeNode Root;
        Data_Access.common conn;
        DataTable tbTree;

        public DataTable TbTree
        {
            get { return tbTree; }
            set { tbTree = value; }
        }
        private int countTotalPositives(DataTable samples)
        {
            int result = 0;

            samples.DefaultView.RowFilter = "tinhhieuqua='Có'";
            result = samples.DefaultView.ToTable().Rows.Count;
            return result;
        }
        /// <summary>
        /// I(S) = -    -     = 0.970  
        /// </summary>
        /// <returns></returns>
        private double Tinh_IS(DataTable tb)
        {
            double S = tb.Rows.Count;            
            tb.DefaultView.RowFilter = "tinhhieuqua='Có'";            
            double C = tb.DefaultView.ToTable().Rows.Count;
            tb.DefaultView.RowFilter = "tinhhieuqua='Không'";
             double K = tb.DefaultView.ToTable().Rows.Count;            
            double IS = (-(K / S) * System.Math.Log(K / S, 2)) - ((C / S) * System.Math.Log(C / S, 2));
            return IS;

        }
        /// <summary>
        /// Tinh Entropy
        /// </summary>
        /// <param name="at"></param>
        /// <returns></returns>
        public double calcEntropy(Attribute at,DataTable tb)
        {
            double Entropy = 0.0;
            double S=0;//= tb.Rows.Count;
            for (int i = 0; i < at.values.Length; i++)
            {

                tb.DefaultView.RowFilter = "tinhhieuqua='Có' and " + at.AttributeName + "='" + at.values[i] + "'";
                double C = tb.DefaultView.ToTable().Rows.Count;
                tb.DefaultView.RowFilter = "tinhhieuqua='Không' and " + at.AttributeName + "='" + at.values[i] + "'";
                double K = tb.DefaultView.ToTable().Rows.Count;
                double EI1=0;
                double EI2=0;
                S = C + K;
                if (C == 0)
                    EI1 = 0;
                else
                 EI1 = (-C / (K + C)) * System.Math.Log((C / (K + C)), 2);
             if (K == 0)
                 EI2=0;
             else
                EI2=(-K / (K + C)) * System.Math.Log((K / (K + C)), 2);                
             
                Entropy += (double)((C + K) / S) *(EI1-EI2) ;

            }
            return Entropy;
        }

        public double Gain(Attribute at,DataTable tb)
        {
            double gain=Tinh_IS( tb) - calcEntropy(at,tb);
            return gain;
        }

        public double SplitInfo(Attribute at, DataTable tb){
            double Split = 0.0;
            for (int i = 0; i < at.values.Length; i++){
                tb.DefaultView.RowFilter = "tinhhieuqua='Có' and " + at.AttributeName + "='" + at.values[i] + "'";
                double C = tb.DefaultView.ToTable().Rows.Count;
                tb.DefaultView.RowFilter = "tinhhieuqua='Không' and " + at.AttributeName + "='" + at.values[i] + "'";
                double K = tb.DefaultView.ToTable().Rows.Count;
                double S = tb.Rows.Count;
                double pl;
                if (C == 0 && K == 0)
                    pl = 0;
                else
                    pl=-((C + K) / S) * (((K + C) / S) * System.Math.Log(((K + C) / S), 2));
                Split +=pl ;
            }
            return Split;
        }

        public double GainRation(Attribute at, DataTable tb)
        {
           double  split=SplitInfo(at, tb);

           double gainration = Gain(at, tb) / split;
            return gainration;
        }
        
        public thuattoanC4(DataTable tb,Attribute[] at)
        {
            Attribute[] attributes_lb;          
            this.tb_Mau = tb;            
            attributes = at;
            tbTree = new DataTable();
            tbTree.Columns.Add("ID", typeof(int));
            tbTree.Columns.Add("SoThich", typeof(String));
            tbTree.Columns.Add("GioiTinh", typeof(String));
            tbTree.Columns.Add("Tuoi", typeof(String));
            tbTree.Columns.Add("Thunhap", typeof(String));
            tbTree.Columns.Add("TinhHieuQua", typeof(String));
            tbTree.Columns.Add("Parent_ID", typeof(String));
            tbTree.Columns.Add("MaSP", typeof(String)); 

        }
        public List<GainRatio> GetGainRatio(Attribute[] attributes, DataTable tb)
        {
            List<GainRatio> arr_GainRatio = new List<GainRatio>(); 
            
            for (int i = 0; i < attributes.Length; i++){
                GainRatio GR = new GainRatio(this.GainRation(attributes[i], tb), attributes[i]);
                     arr_GainRatio.Add(GR);
            }
            for (int i = 0; i < arr_GainRatio.Count - 1; i++)
            {
                for (int j = i + 1; j < arr_GainRatio.Count; j++)
                {
                    if ((arr_GainRatio[j]).value > (arr_GainRatio[i]).value)
                    {
                        GainRatio temp =arr_GainRatio[i];
                        arr_GainRatio[i] = arr_GainRatio[j];
                        arr_GainRatio[j] = temp;

                    }
                }
            }
            return arr_GainRatio;
        }
        private DataRow GetRow(int count, object Parent_ID, object value, object tinhhieuqua, GainRatio gar,String MaSP)
        {
            DataRow row = this.tbTree.NewRow();
            row["ID"] = count;
            switch (gar.at.AttributeName)
            {
                case "SoThich":
                    row["SoThich"] = value;//gar.at.mlable;
                    row["GioiTinh"] = DBNull.Value;
                    row["Tuoi"] = DBNull.Value;
                    row["Thunhap"] = DBNull.Value;
                    row["TinhHieuQua"] = tinhhieuqua;
                    row["Parent_ID"] = Parent_ID;
                    row["MaSP"] = MaSP;
                    break;
                case "GioiTinh":
                    row["SoThich"] = DBNull.Value;
                    row["GioiTinh"] = value;//gar.at.mlable;
                    row["Tuoi"] = DBNull.Value;
                    row["Thunhap"] = DBNull.Value;
                    row["TinhHieuQua"] = tinhhieuqua;
                    row["Parent_ID"] = Parent_ID;
                    row["MaSP"] = MaSP;
                    break;
                case "Tuoi":
                    row["SoThich"] = DBNull.Value;
                    row["GioiTinh"] = DBNull.Value;
                    row["Tuoi"] = value;//gar.at.mlable;
                    row["Thunhap"] = DBNull.Value;
                    row["TinhHieuQua"] = tinhhieuqua;
                    row["Parent_ID"] = Parent_ID;
                    row["MaSP"] = MaSP;
                    break;
                case "Thunhap":
                    row["SoThich"] = DBNull.Value;
                    row["GioiTinh"] = DBNull.Value;
                    row["Tuoi"] = DBNull.Value;//gar.at.mlable;
                    row["Thunhap"] = value;
                    row["TinhHieuQua"] = tinhhieuqua;
                    row["Parent_ID"] = Parent_ID;
                    row["MaSP"] = MaSP;
                    break;

            }
            return row;

        }
        public DataTable CreateTree()
        {
            List<GainRatio> arr_GainRatio = new List<GainRatio>();
            arr_GainRatio = GetGainRatio(attributes,tb_Mau);
            
            DataRow row = GetRow(1, 0, DBNull.Value, DBNull.Value, arr_GainRatio[0],"");          
            
            this.tbTree.Rows.Add(row);

            Dequyvecay(tb_Mau, arr_GainRatio[0], 1, attributes, 1);
            this.tbTree.TableName = "tbtree";
            String path = AppDomain.CurrentDomain.BaseDirectory;
            this.tbTree.WriteXml(path + @"\App_Data\tree.xml", XmlWriteMode.WriteSchema);
            return this.tbTree;
            
           
        }
        public Attribute[] delete(Attribute[] at,String Name)
        {  Boolean flag=true;
            Attribute[] at1=new Attribute[at.Length-1]; 
            for (int i = 0; i < at.Length-1; i++)
            {
                if (at[i].AttributeName.Equals(Name))
                    flag = false;
                else
                    at1[i] = at[i];
               if (flag == false)
                   at1[i] = at[i + 1];
             }
            
             return at1;
        }
        public void Dequyvecay(DataTable tb, GainRatio gr, int ID_Parent, Attribute[] attributes, int count)
        {            
            
                for (int i = 0; i < gr.at.values.Length; i++){
                
                 tb.DefaultView.RowFilter = "tinhhieuqua='Có' and " + gr.at.AttributeName + "='" + gr.at.values[i] + "'";
                    DataTable tb_C=tb.DefaultView.ToTable().Copy();
                    double C = tb_C.Rows.Count;
                    tb.DefaultView.RowFilter = "tinhhieuqua='Không' and " + gr.at.AttributeName + "='" + gr.at.values[i] + "'";
                    DataTable tb_K = tb.DefaultView.ToTable().Copy();
                    double KK = tb_K.Rows.Count;
                             
                        if (C > 0 && KK <= 0)  {
                           // foreach (DataRow rowc in tb_C.Rows){
                            DataRow row = GetRow(this.tbTree.Rows.Count + 1, ID_Parent, gr.at.values[i], "Có", gr, tb_C.Rows[0]["MaSP"].ToString() );                               
                                 this.tbTree.Rows.Add(row);
                           // }
                        
                        }
                        else if (KK > 0 && C <= 0){                        
                        
                            //foreach (DataRow rowk in tb_K.Rows)
                            //{

                            DataRow row = GetRow(this.tbTree.Rows.Count + 1, ID_Parent, gr.at.values[i], "Không", gr, tb_K.Rows[0]["MaSP"].ToString());
                               
                                this.tbTree.Rows.Add(row);
                           // }

                        }
                      
                        if (C > 0 && KK > 0){
                           
                            tb.DefaultView.RowFilter =  gr.at.AttributeName + "='" + gr.at.values[i]+"'" ;
                            DataTable tb1 = tb.DefaultView.ToTable().Copy();                            
                            Attribute[] attributes1=this.delete(attributes, gr.at.AttributeName);
                          
                            if (attributes1.Length > 0)
                             {
                                 List<GainRatio> arr_GainRatio1 = GetGainRatio(attributes1, tb1);    
                                     int count_child = this.tbTree.Rows.Count + 1;                                                                 
                                     count = this.tbTree.Rows.Count + 1;
                                     DataRow row = GetRow(this.tbTree.Rows.Count + 1, ID_Parent, gr.at.values[i], DBNull.Value, gr,tb1.Rows[0]["MaSP"].ToString());
                                     this.tbTree.Rows.Add(row);      
                                     Dequyvecay(tb1, arr_GainRatio1[0], count_child, attributes1, count);
                                    
                            }
                                 
                    }
                }               
              
            }
        }
     }