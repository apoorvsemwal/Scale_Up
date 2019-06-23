using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace TestConcepts
{
	/// <summary>
	/// Interaction logic for MainWindow.xaml
	/// </summary>
	public partial class MainWindow : Window
	{

		public Person person { get; set; }

		public MainWindow()
		{
			InitializeComponent();
			MySlider.Value = 45;
			MyTxt.Text = MySlider.Value.ToString();

			person = new Person()
			{
				Name = "Apoorv Semwal"
			};

			//this.DataContext = person;

		}

		private void Button_Click(object sender, RoutedEventArgs e)
		{
			MessageBox.Show("I am a box!");
		}

	}
}
