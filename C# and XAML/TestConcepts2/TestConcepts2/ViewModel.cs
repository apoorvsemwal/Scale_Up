using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Input;

namespace TestConcepts2
{
	class ViewModel
	{
		public ICommand MyCommand { get; set; }
		public ViewModel()
		{
			MyCommand = new Command(executeMethod, canExecuteMethod);
		}
		private bool canExecuteMethod(object parameter)
		{
			return true;
		}

		private void executeMethod(object parameter) {
			MessageBox.Show("Command executed no Code-Behind");
		}
	}
}
