package br.com.product.usecases

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import spock.lang.Specification

class Spec extends Specification {
    def setupSpec() {
        FixtureFactoryLoader.loadTemplates("br.com.product.templates")
    }
}
