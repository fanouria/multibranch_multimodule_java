package gr.codehub.core.showcase.testing;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("gr.codehub.core.showcase.testing")
@IncludeTags("development")
public class SuitDemo {

}
