using MVVMDemoNew.ViewModels.Commands;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace MVVMDemoNew.ViewModels
{
	public class ViewModelBase
	{
		public SimpleCommand SimpleCommand { get; set; }
		public ParameterizedCommand ParameterizedCommand { get; set; }

		public ViewModelBase()
		{
			this.SimpleCommand = new SimpleCommand(new Action(SimpleMethod));
			this.ParameterizedCommand = new ParameterizedCommand(new Action<String>(ParameterizedMethod));
		}

		public void SimpleMethod()
		{
			MessageBox.Show("It was a simple non parameterized command!");
		}

		public void ParameterizedMethod(string person)
		{
			MessageBox.Show(string.Format("Hello {0}. It's a parameterized command!",person));
		}

	}
}
