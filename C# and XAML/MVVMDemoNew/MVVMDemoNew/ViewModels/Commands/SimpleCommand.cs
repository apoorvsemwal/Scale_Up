using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace MVVMDemoNew.ViewModels.Commands
{
	public class SimpleCommand : ICommand
	{
		private Action _execute;

		public SimpleCommand(Action execute)
		{
			this._execute = execute;
		}

		public event EventHandler CanExecuteChanged;

		public bool CanExecute(object parameter)
		{
			return true;
		}

		public void Execute(object parameter)
		{
			_execute();
		}
	}
}
