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

namespace TestConcepts2
{
	/// <summary>
	/// Interaction logic for MainWindow.xaml
	/// </summary>
	public partial class MainWindow : Window
	{
		public Person personProp { get; set; }

		public MainWindow()
		{
			InitializeComponent();

			this.DataContext = new Person {Firstname = "Apoorv", Lastname = "Semwal" };
		}

		private void Click_Click(object sender, RoutedEventArgs e)
		{
			MessageBox.Show("Clicked Outer Button.");
		}

		private void outerEllipse_MouseDown(object sender, MouseButtonEventArgs e)
		{
			this.Title = "Green Ellipse changed the title!";
		}

		private void innerButton_Click(object sender, RoutedEventArgs e)
		{
			MessageBox.Show("Clicked Inner Button.");
		}

		private void outerButton_PreviewMouse(object sender, MouseEventArgs e)
		{
			MessageBox.Show("Mouse Moved by outer Button!");
		}

		private void innerButton_PreviewMouse(object sender, MouseEventArgs e)
		{
			MessageBox.Show("Mouse Moved by inner Button!");
		}
	}
}
