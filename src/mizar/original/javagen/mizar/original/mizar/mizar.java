// HASH COLLISIONS: YES
// timestamp: 1785512695559

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
public final class mizar extends FlowRuntime {

	// Init hosts: 2
	private static final void init_hosts() {
		FlowRuntime.registerNativeHost(FlowFileSystem.class);
		FlowRuntime.registerNativeHost(Native.class);
	}

	// Init modules: 16
	private static final void init_modules() {
		Module_lexicon.init();
		Module_limits.init();
		Module_url.init();
		Module_securitymode.init();
		Module_runtime.init();
		Module_builtin.init();
		Module_math.init();
		Module_bits.init();
		Module_correl.init();
		Module_url_parameter.init();
		Module_devmode.init();
		Module_equalizer.init();
		Module_target.init();
		Module_unifier.init();
		Module_xml_parser.init();
		Module_prep.init();
	}
	private static final void init() {
		Structs.init();
		init_hosts();
		init_modules();
	}
	protected void main() {
		init();
		Module_mizar.f_main();
	}
	public static void main(String[] args) {
		FlowRuntime.program_args = args;
		mizar runner = new mizar();
		runner.start();
	}
}
