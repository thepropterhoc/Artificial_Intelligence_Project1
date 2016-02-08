#Project 1 Flow Specification 
### Shelby Vanhooser, Nick Sparks  

[![Join the chat at https://gitter.im/thepropterhoc/Artificial_Intelligence_Project1](https://badges.gitter.im/thepropterhoc/Artificial_Intelligence_Project1.svg)](https://gitter.im/thepropterhoc/Artificial_Intelligence_Project1?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

___
___

.
├── LICENSE
├── README.md
├── build
│   ├── META-INF
│   │   ├── MANIFEST.MF
│   │   ├── maven
│   │   │   └── com.thoughtworks.xstream
│   │   │       └── xstream
│   │   │           ├── pom.properties
│   │   │           └── pom.xml
│   │   └── services
│   │       └── org.xmlpull.v1.XmlPullParserFactory
│   ├── com
│   │   ├── martiansoftware
│   │   │   ├── jsap
│   │   │   │   ├── CommandLineTokenizer.class
│   │   │   │   ├── DefaultSource.class
│   │   │   │   ├── Defaults.class
│   │   │   │   ├── ExceptionMap.class
│   │   │   │   ├── Flagged.class
│   │   │   │   ├── FlaggedOption.class
│   │   │   │   ├── IDMap.class
│   │   │   │   ├── IllegalMultipleDeclarationException.class
│   │   │   │   ├── JSAP.class
│   │   │   │   ├── JSAPException.class
│   │   │   │   ├── JSAPResult.class
│   │   │   │   ├── Option.class
│   │   │   │   ├── Parameter.class
│   │   │   │   ├── ParseException.class
│   │   │   │   ├── Parser.class
│   │   │   │   ├── PropertyStringParser.class
│   │   │   │   ├── QualifiedSwitch.class
│   │   │   │   ├── RequiredParameterMissingException.class
│   │   │   │   ├── SimpleJSAP.class
│   │   │   │   ├── StringParser.class
│   │   │   │   ├── Switch.class
│   │   │   │   ├── SyntaxException.class
│   │   │   │   ├── UnflaggedOption.class
│   │   │   │   ├── UnknownFlagException.class
│   │   │   │   ├── UnspecifiedParameterException.class
│   │   │   │   ├── ant
│   │   │   │   │   ├── DefaultValue.class
│   │   │   │   │   ├── FlaggedOptionConfiguration.class
│   │   │   │   │   ├── JSAPAntTask.class
│   │   │   │   │   ├── OptionConfiguration.class
│   │   │   │   │   ├── ParameterConfiguration.class
│   │   │   │   │   ├── ParserProperty.class
│   │   │   │   │   ├── SwitchConfiguration.class
│   │   │   │   │   └── UnflaggedOptionConfiguration.class
│   │   │   │   ├── defaultsources
│   │   │   │   │   └── PropertyDefaultSource.class
│   │   │   │   ├── stringparsers
│   │   │   │   │   ├── BigDecimalStringParser.class
│   │   │   │   │   ├── BigIntegerStringParser.class
│   │   │   │   │   ├── BooleanStringParser.class
│   │   │   │   │   ├── ByteStringParser.class
│   │   │   │   │   ├── CharacterStringParser.class
│   │   │   │   │   ├── ClassStringParser.class
│   │   │   │   │   ├── ColorStringParser.class
│   │   │   │   │   ├── DateStringParser.class
│   │   │   │   │   ├── DoubleStringParser.class
│   │   │   │   │   ├── EnumeratedStringParser.class
│   │   │   │   │   ├── FileStringParser.class
│   │   │   │   │   ├── FloatStringParser.class
│   │   │   │   │   ├── ForNameStringParser.class
│   │   │   │   │   ├── InetAddressStringParser.class
│   │   │   │   │   ├── IntSizeStringParser.class
│   │   │   │   │   ├── IntegerStringParser.class
│   │   │   │   │   ├── LongSizeStringParser.class
│   │   │   │   │   ├── LongStringParser.class
│   │   │   │   │   ├── PackageStringParser.class
│   │   │   │   │   ├── ShortStringParser.class
│   │   │   │   │   ├── StringStringParser.class
│   │   │   │   │   └── URLStringParser.class
│   │   │   │   └── xml
│   │   │   │       ├── FlaggedConfig.class
│   │   │   │       ├── FlaggedOptionConfig.class
│   │   │   │       ├── JSAPConfig.class
│   │   │   │       ├── JSAPXStream.class
│   │   │   │       ├── ParameterConfig.class
│   │   │   │       ├── Property.class
│   │   │   │       ├── QualifiedSwitchConfig.class
│   │   │   │       ├── StringParserConfig.class
│   │   │   │       ├── SwitchConfig.class
│   │   │   │       ├── TryDumpXML.class
│   │   │   │       ├── TryLoadXML.class
│   │   │   │       └── UnflaggedOptionConfig.class
│   │   │   └── util
│   │   │       └── StringUtils.class
│   │   └── thoughtworks
│   │       └── xstream
│   │           ├── InitializationException.class
│   │           ├── MarshallingStrategy.class
│   │           ├── XStream$1.class
│   │           ├── XStream$2.class
│   │           ├── XStream$InitializationException.class
│   │           ├── XStream.class
│   │           ├── XStreamException.class
│   │           ├── XStreamer.class
│   │           ├── annotations
│   │           │   ├── AnnotationProvider.class
│   │           │   ├── AnnotationReflectionConverter.class
│   │           │   ├── Annotations.class
│   │           │   ├── XStreamAlias.class
│   │           │   ├── XStreamAsAttribute.class
│   │           │   ├── XStreamContainedType.class
│   │           │   ├── XStreamConverter.class
│   │           │   ├── XStreamConverters.class
│   │           │   ├── XStreamImplicit.class
│   │           │   ├── XStreamImplicitCollection.class
│   │           │   ├── XStreamInclude.class
│   │           │   └── XStreamOmitField.class
│   │           ├── converters
│   │           │   ├── ConversionException.class
│   │           │   ├── Converter.class
│   │           │   ├── ConverterLookup.class
│   │           │   ├── ConverterMatcher.class
│   │           │   ├── ConverterRegistry.class
│   │           │   ├── DataHolder.class
│   │           │   ├── ErrorReporter.class
│   │           │   ├── ErrorWriter.class
│   │           │   ├── MarshallingContext.class
│   │           │   ├── SingleValueConverter.class
│   │           │   ├── SingleValueConverterWrapper.class
│   │           │   ├── UnmarshallingContext.class
│   │           │   ├── basic
│   │           │   │   ├── AbstractSingleValueConverter.class
│   │           │   │   ├── BigDecimalConverter.class
│   │           │   │   ├── BigIntegerConverter.class
│   │           │   │   ├── BooleanConverter.class
│   │           │   │   ├── ByteConverter.class
│   │           │   │   ├── CharConverter.class
│   │           │   │   ├── DateConverter.class
│   │           │   │   ├── DoubleConverter.class
│   │           │   │   ├── FloatConverter.class
│   │           │   │   ├── IntConverter.class
│   │           │   │   ├── LongConverter.class
│   │           │   │   ├── NullConverter.class
│   │           │   │   ├── ShortConverter.class
│   │           │   │   ├── StringBufferConverter.class
│   │           │   │   ├── StringBuilderConverter.class
│   │           │   │   ├── StringConverter.class
│   │           │   │   ├── URIConverter.class
│   │           │   │   ├── URLConverter.class
│   │           │   │   └── UUIDConverter.class
│   │           │   ├── collections
│   │           │   │   ├── AbstractCollectionConverter.class
│   │           │   │   ├── ArrayConverter.class
│   │           │   │   ├── BitSetConverter.class
│   │           │   │   ├── CharArrayConverter.class
│   │           │   │   ├── CollectionConverter.class
│   │           │   │   ├── MapConverter.class
│   │           │   │   ├── PropertiesConverter.class
│   │           │   │   ├── SingletonCollectionConverter.class
│   │           │   │   ├── SingletonMapConverter.class
│   │           │   │   ├── TreeMapConverter$1.class
│   │           │   │   ├── TreeMapConverter$NullComparator.class
│   │           │   │   ├── TreeMapConverter.class
│   │           │   │   ├── TreeSetConverter$1$1.class
│   │           │   │   ├── TreeSetConverter$1.class
│   │           │   │   └── TreeSetConverter.class
│   │           │   ├── enums
│   │           │   │   ├── EnumConverter.class
│   │           │   │   ├── EnumMapConverter.class
│   │           │   │   ├── EnumSetConverter.class
│   │           │   │   └── EnumSingleValueConverter.class
│   │           │   ├── extended
│   │           │   │   ├── CharsetConverter.class
│   │           │   │   ├── ColorConverter.class
│   │           │   │   ├── CurrencyConverter.class
│   │           │   │   ├── DurationConverter$1.class
│   │           │   │   ├── DurationConverter.class
│   │           │   │   ├── DynamicProxyConverter$1.class
│   │           │   │   ├── DynamicProxyConverter.class
│   │           │   │   ├── EncodedByteArrayConverter.class
│   │           │   │   ├── FileConverter.class
│   │           │   │   ├── FontConverter.class
│   │           │   │   ├── GregorianCalendarConverter.class
│   │           │   │   ├── ISO8601DateConverter.class
│   │           │   │   ├── ISO8601GregorianCalendarConverter.class
│   │           │   │   ├── ISO8601SqlTimestampConverter.class
│   │           │   │   ├── JavaClassConverter.class
│   │           │   │   ├── JavaFieldConverter.class
│   │           │   │   ├── JavaMethodConverter.class
│   │           │   │   ├── LocaleConverter.class
│   │           │   │   ├── LookAndFeelConverter.class
│   │           │   │   ├── PropertyEditorCapableConverter.class
│   │           │   │   ├── RegexPatternConverter.class
│   │           │   │   ├── SqlDateConverter.class
│   │           │   │   ├── SqlTimeConverter.class
│   │           │   │   ├── SqlTimestampConverter.class
│   │           │   │   ├── StackTraceElementConverter.class
│   │           │   │   ├── StackTraceElementFactory.class
│   │           │   │   ├── SubjectConverter.class
│   │           │   │   ├── TextAttributeConverter.class
│   │           │   │   ├── ThrowableConverter.class
│   │           │   │   ├── ToAttributedValueConverter$1.class
│   │           │   │   ├── ToAttributedValueConverter.class
│   │           │   │   └── ToStringConverter.class
│   │           │   ├── javabean
│   │           │   │   ├── BeanProperty.class
│   │           │   │   ├── BeanProvider$Visitor.class
│   │           │   │   ├── BeanProvider.class
│   │           │   │   ├── ComparingPropertySorter.class
│   │           │   │   ├── JavaBeanConverter$1.class
│   │           │   │   ├── JavaBeanConverter$2.class
│   │           │   │   ├── JavaBeanConverter$DuplicateFieldException.class
│   │           │   │   ├── JavaBeanConverter$DuplicatePropertyException.class
│   │           │   │   ├── JavaBeanConverter.class
│   │           │   │   ├── JavaBeanProvider$Visitor.class
│   │           │   │   ├── JavaBeanProvider.class
│   │           │   │   ├── NativePropertySorter.class
│   │           │   │   ├── PropertyDictionary.class
│   │           │   │   └── PropertySorter.class
│   │           │   └── reflection
│   │           │       ├── AbstractAttributedCharacterIteratorAttributeConverter.class
│   │           │       ├── AbstractReflectionConverter$1.class
│   │           │       ├── AbstractReflectionConverter$2.class
│   │           │       ├── AbstractReflectionConverter$3.class
│   │           │       ├── AbstractReflectionConverter$ArraysList.class
│   │           │       ├── AbstractReflectionConverter$DuplicateFieldException.class
│   │           │       ├── AbstractReflectionConverter$FieldInfo.class
│   │           │       ├── AbstractReflectionConverter$MappingList.class
│   │           │       ├── AbstractReflectionConverter$UnknownFieldException.class
│   │           │       ├── AbstractReflectionConverter.class
│   │           │       ├── CGLIBEnhancedConverter$CGLIBFilteringReflectionProvider$1.class
│   │           │       ├── CGLIBEnhancedConverter$CGLIBFilteringReflectionProvider.class
│   │           │       ├── CGLIBEnhancedConverter$ReverseEngineeredCallbackFilter.class
│   │           │       ├── CGLIBEnhancedConverter$ReverseEngineeringInvocationHandler.class
│   │           │       ├── CGLIBEnhancedConverter.class
│   │           │       ├── ExternalizableConverter$1.class
│   │           │       ├── ExternalizableConverter$2.class
│   │           │       ├── ExternalizableConverter.class
│   │           │       ├── FieldDictionary.class
│   │           │       ├── FieldKey.class
│   │           │       ├── FieldKeySorter.class
│   │           │       ├── ImmutableFieldKeySorter.class
│   │           │       ├── MissingFieldException.class
│   │           │       ├── NativeFieldKeySorter$1.class
│   │           │       ├── NativeFieldKeySorter.class
│   │           │       ├── ObjectAccessException.class
│   │           │       ├── PureJavaReflectionProvider$1.class
│   │           │       ├── PureJavaReflectionProvider.class
│   │           │       ├── ReflectionConverter.class
│   │           │       ├── ReflectionProvider$Visitor.class
│   │           │       ├── ReflectionProvider.class
│   │           │       ├── ReflectionProviderWrapper.class
│   │           │       ├── SelfStreamingInstanceChecker.class
│   │           │       ├── SerializableConverter$1.class
│   │           │       ├── SerializableConverter$2$1.class
│   │           │       ├── SerializableConverter$2.class
│   │           │       ├── SerializableConverter$UnserializableParentsReflectionProvider$1.class
│   │           │       ├── SerializableConverter$UnserializableParentsReflectionProvider.class
│   │           │       ├── SerializableConverter.class
│   │           │       ├── SerializationMethodInvoker$1.class
│   │           │       ├── SerializationMethodInvoker.class
│   │           │       ├── SortableFieldKeySorter$FieldComparator.class
│   │           │       ├── SortableFieldKeySorter.class
│   │           │       ├── Sun14ReflectionProvider.class
│   │           │       ├── XStream12FieldKeySorter$1.class
│   │           │       └── XStream12FieldKeySorter.class
│   │           ├── core
│   │           │   ├── AbstractReferenceMarshaller$1.class
│   │           │   ├── AbstractReferenceMarshaller$Id.class
│   │           │   ├── AbstractReferenceMarshaller$ReferencedImplicitElementException.class
│   │           │   ├── AbstractReferenceMarshaller.class
│   │           │   ├── AbstractReferenceUnmarshaller.class
│   │           │   ├── AbstractTreeMarshallingStrategy.class
│   │           │   ├── BaseException.class
│   │           │   ├── Caching.class
│   │           │   ├── DefaultConverterLookup.class
│   │           │   ├── JVM$1.class
│   │           │   ├── JVM.class
│   │           │   ├── MapBackedDataHolder.class
│   │           │   ├── ReferenceByIdMarshaller$IDGenerator.class
│   │           │   ├── ReferenceByIdMarshaller.class
│   │           │   ├── ReferenceByIdMarshallingStrategy.class
│   │           │   ├── ReferenceByIdUnmarshaller.class
│   │           │   ├── ReferenceByXPathMarshaller.class
│   │           │   ├── ReferenceByXPathMarshallingStrategy.class
│   │           │   ├── ReferenceByXPathUnmarshaller.class
│   │           │   ├── ReferencingMarshallingContext.class
│   │           │   ├── SequenceGenerator.class
│   │           │   ├── TreeMarshaller$CircularReferenceException.class
│   │           │   ├── TreeMarshaller.class
│   │           │   ├── TreeMarshallingStrategy.class
│   │           │   ├── TreeUnmarshaller.class
│   │           │   └── util
│   │           │       ├── ArrayIterator.class
│   │           │       ├── Base64Encoder.class
│   │           │       ├── ClassLoaderReference$Replacement.class
│   │           │       ├── ClassLoaderReference.class
│   │           │       ├── Cloneables.class
│   │           │       ├── CompositeClassLoader$1.class
│   │           │       ├── CompositeClassLoader.class
│   │           │       ├── CustomObjectInputStream$CustomGetField.class
│   │           │       ├── CustomObjectInputStream$StreamCallback.class
│   │           │       ├── CustomObjectInputStream.class
│   │           │       ├── CustomObjectOutputStream$1.class
│   │           │       ├── CustomObjectOutputStream$CustomPutField.class
│   │           │       ├── CustomObjectOutputStream$StreamCallback.class
│   │           │       ├── CustomObjectOutputStream.class
│   │           │       ├── DependencyInjectionFactory$1.class
│   │           │       ├── DependencyInjectionFactory$TypedValue.class
│   │           │       ├── DependencyInjectionFactory.class
│   │           │       ├── FastField.class
│   │           │       ├── FastStack.class
│   │           │       ├── Fields.class
│   │           │       ├── HierarchicalStreams.class
│   │           │       ├── ObjectIdDictionary$IdWrapper.class
│   │           │       ├── ObjectIdDictionary$WeakIdWrapper.class
│   │           │       ├── ObjectIdDictionary$Wrapper.class
│   │           │       ├── ObjectIdDictionary.class
│   │           │       ├── OrderRetainingMap$1.class
│   │           │       ├── OrderRetainingMap$ArraySet.class
│   │           │       ├── OrderRetainingMap.class
│   │           │       ├── Pool$Factory.class
│   │           │       ├── Pool.class
│   │           │       ├── PresortedMap$1.class
│   │           │       ├── PresortedMap$ArraySet.class
│   │           │       ├── PresortedMap$ArraySetComparator.class
│   │           │       ├── PresortedMap.class
│   │           │       ├── PresortedSet.class
│   │           │       ├── Primitives.class
│   │           │       ├── PrioritizedList$PrioritizedItem.class
│   │           │       ├── PrioritizedList$PrioritizedItemIterator.class
│   │           │       ├── PrioritizedList.class
│   │           │       ├── QuickWriter.class
│   │           │       ├── ThreadSafePropertyEditor$1.class
│   │           │       ├── ThreadSafePropertyEditor.class
│   │           │       ├── ThreadSafeSimpleDateFormat$1.class
│   │           │       ├── ThreadSafeSimpleDateFormat.class
│   │           │       ├── TypedNull.class
│   │           │       ├── WeakCache$1.class
│   │           │       ├── WeakCache$2.class
│   │           │       ├── WeakCache$3.class
│   │           │       ├── WeakCache$4$1.class
│   │           │       ├── WeakCache$4.class
│   │           │       ├── WeakCache$Visitor.class
│   │           │       ├── WeakCache.class
│   │           │       └── XmlHeaderAwareReader.class
│   │           ├── io
│   │           │   ├── AbstractDriver.class
│   │           │   ├── AbstractReader.class
│   │           │   ├── AbstractWriter.class
│   │           │   ├── AttributeNameIterator.class
│   │           │   ├── ExtendedHierarchicalStreamReader.class
│   │           │   ├── ExtendedHierarchicalStreamWriter.class
│   │           │   ├── ExtendedHierarchicalStreamWriterHelper.class
│   │           │   ├── HierarchicalStreamDriver.class
│   │           │   ├── HierarchicalStreamReader.class
│   │           │   ├── HierarchicalStreamWriter.class
│   │           │   ├── ReaderWrapper.class
│   │           │   ├── StatefulWriter.class
│   │           │   ├── StreamException.class
│   │           │   ├── WriterWrapper.class
│   │           │   ├── binary
│   │           │   │   ├── BinaryStreamDriver.class
│   │           │   │   ├── BinaryStreamReader$1.class
│   │           │   │   ├── BinaryStreamReader$IdRegistry.class
│   │           │   │   ├── BinaryStreamReader.class
│   │           │   │   ├── BinaryStreamWriter$1.class
│   │           │   │   ├── BinaryStreamWriter$IdRegistry.class
│   │           │   │   ├── BinaryStreamWriter.class
│   │           │   │   ├── ReaderDepthState$1.class
│   │           │   │   ├── ReaderDepthState$Attribute.class
│   │           │   │   ├── ReaderDepthState$State.class
│   │           │   │   ├── ReaderDepthState.class
│   │           │   │   ├── Token$Attribute.class
│   │           │   │   ├── Token$EndNode.class
│   │           │   │   ├── Token$Formatter.class
│   │           │   │   ├── Token$MapIdToValue.class
│   │           │   │   ├── Token$StartNode.class
│   │           │   │   ├── Token$Value.class
│   │           │   │   └── Token.class
│   │           │   ├── copy
│   │           │   │   └── HierarchicalStreamCopier.class
│   │           │   ├── json
│   │           │   │   ├── AbstractJsonWriter$IllegalWriterStateException.class
│   │           │   │   ├── AbstractJsonWriter$StackElement.class
│   │           │   │   ├── AbstractJsonWriter$Type.class
│   │           │   │   ├── AbstractJsonWriter.class
│   │           │   │   ├── JettisonMappedXmlDriver.class
│   │           │   │   ├── JettisonStaxWriter.class
│   │           │   │   ├── JsonHierarchicalStreamDriver.class
│   │           │   │   ├── JsonHierarchicalStreamWriter.class
│   │           │   │   ├── JsonWriter$Format.class
│   │           │   │   └── JsonWriter.class
│   │           │   ├── naming
│   │           │   │   ├── NameCoder.class
│   │           │   │   ├── NameCoderWrapper.class
│   │           │   │   ├── NoNameCoder.class
│   │           │   │   └── StaticNameCoder.class
│   │           │   ├── path
│   │           │   │   ├── Path.class
│   │           │   │   ├── PathTracker.class
│   │           │   │   ├── PathTrackingReader.class
│   │           │   │   └── PathTrackingWriter.class
│   │           │   └── xml
│   │           │       ├── AbstractDocumentReader$1.class
│   │           │       ├── AbstractDocumentReader$Pointer.class
│   │           │       ├── AbstractDocumentReader.class
│   │           │       ├── AbstractDocumentWriter.class
│   │           │       ├── AbstractPullReader$1.class
│   │           │       ├── AbstractPullReader$Event.class
│   │           │       ├── AbstractPullReader.class
│   │           │       ├── AbstractXmlDriver.class
│   │           │       ├── AbstractXmlReader.class
│   │           │       ├── AbstractXmlWriter.class
│   │           │       ├── AbstractXppDomDriver.class
│   │           │       ├── AbstractXppDriver.class
│   │           │       ├── BEAStaxDriver.class
│   │           │       ├── CompactWriter.class
│   │           │       ├── DocumentReader.class
│   │           │       ├── DocumentWriter.class
│   │           │       ├── Dom4JDriver$1.class
│   │           │       ├── Dom4JDriver.class
│   │           │       ├── Dom4JReader.class
│   │           │       ├── Dom4JWriter.class
│   │           │       ├── Dom4JXmlWriter.class
│   │           │       ├── DomDriver.class
│   │           │       ├── DomReader.class
│   │           │       ├── DomWriter.class
│   │           │       ├── JDomDriver.class
│   │           │       ├── JDomReader.class
│   │           │       ├── JDomWriter.class
│   │           │       ├── KXml2DomDriver.class
│   │           │       ├── KXml2Driver.class
│   │           │       ├── PrettyPrintWriter.class
│   │           │       ├── QNameMap.class
│   │           │       ├── SaxWriter.class
│   │           │       ├── SjsxpDriver.class
│   │           │       ├── StaxDriver.class
│   │           │       ├── StaxReader.class
│   │           │       ├── StaxWriter.class
│   │           │       ├── TraxSource.class
│   │           │       ├── WstxDriver.class
│   │           │       ├── XStream11NameCoder.class
│   │           │       ├── XStream11XmlFriendlyReplacer.class
│   │           │       ├── XmlFriendlyNameCoder$1IntPairList.class
│   │           │       ├── XmlFriendlyNameCoder$IntPair.class
│   │           │       ├── XmlFriendlyNameCoder.class
│   │           │       ├── XmlFriendlyReader.class
│   │           │       ├── XmlFriendlyReplacer.class
│   │           │       ├── XmlFriendlyWriter.class
│   │           │       ├── XomDriver.class
│   │           │       ├── XomReader.class
│   │           │       ├── XomWriter.class
│   │           │       ├── Xpp3DomDriver.class
│   │           │       ├── Xpp3Driver.class
│   │           │       ├── XppDomDriver.class
│   │           │       ├── XppDomReader.class
│   │           │       ├── XppDomWriter.class
│   │           │       ├── XppDriver.class
│   │           │       ├── XppReader.class
│   │           │       └── xppdom
│   │           │           ├── Xpp3Dom.class
│   │           │           ├── Xpp3DomBuilder.class
│   │           │           ├── XppDom.class
│   │           │           ├── XppDomComparator.class
│   │           │           └── XppFactory.class
│   │           ├── mapper
│   │           │   ├── AbstractAttributeAliasingMapper.class
│   │           │   ├── AbstractXmlFriendlyMapper.class
│   │           │   ├── AnnotationConfiguration.class
│   │           │   ├── AnnotationMapper$1.class
│   │           │   ├── AnnotationMapper$UnprocessedTypesSet.class
│   │           │   ├── AnnotationMapper.class
│   │           │   ├── ArrayMapper.class
│   │           │   ├── AttributeAliasingMapper.class
│   │           │   ├── AttributeMapper.class
│   │           │   ├── CGLIBMapper$Marker.class
│   │           │   ├── CGLIBMapper.class
│   │           │   ├── CachingMapper.class
│   │           │   ├── CannotResolveClassException.class
│   │           │   ├── ClassAliasingMapper.class
│   │           │   ├── DefaultImplementationsMapper.class
│   │           │   ├── DefaultMapper.class
│   │           │   ├── DynamicProxyMapper$DynamicProxy.class
│   │           │   ├── DynamicProxyMapper.class
│   │           │   ├── EnumMapper.class
│   │           │   ├── FieldAliasingMapper.class
│   │           │   ├── ImmutableTypesMapper.class
│   │           │   ├── ImplicitCollectionMapper$1.class
│   │           │   ├── ImplicitCollectionMapper$ImplicitCollectionMapperForClass.class
│   │           │   ├── ImplicitCollectionMapper$ImplicitCollectionMappingImpl.class
│   │           │   ├── ImplicitCollectionMapper$NamedItemType.class
│   │           │   ├── ImplicitCollectionMapper.class
│   │           │   ├── LocalConversionMapper.class
│   │           │   ├── Mapper$ImplicitCollectionMapping.class
│   │           │   ├── Mapper$Null.class
│   │           │   ├── Mapper.class
│   │           │   ├── MapperWrapper.class
│   │           │   ├── OuterClassMapper.class
│   │           │   ├── PackageAliasingMapper$1.class
│   │           │   ├── PackageAliasingMapper.class
│   │           │   ├── SystemAttributeAliasingMapper.class
│   │           │   ├── XStream11XmlFriendlyMapper.class
│   │           │   └── XmlFriendlyMapper.class
│   │           └── persistence
│   │               ├── AbstractFilePersistenceStrategy$ValidFilenameFilter.class
│   │               ├── AbstractFilePersistenceStrategy$XmlMapEntriesIterator$1.class
│   │               ├── AbstractFilePersistenceStrategy$XmlMapEntriesIterator.class
│   │               ├── AbstractFilePersistenceStrategy.class
│   │               ├── FilePersistenceStrategy.class
│   │               ├── FileStreamStrategy.class
│   │               ├── PersistenceStrategy.class
│   │               ├── StreamStrategy.class
│   │               ├── XmlArrayList.class
│   │               ├── XmlMap$XmlMapEntries.class
│   │               ├── XmlMap.class
│   │               └── XmlSet.class
│   ├── org
│   │   ├── kxml2
│   │   │   ├── io
│   │   │   │   ├── KXmlParser.class
│   │   │   │   └── KXmlSerializer.class
│   │   │   ├── kdom
│   │   │   │   ├── Document.class
│   │   │   │   ├── Element.class
│   │   │   │   └── Node.class
│   │   │   └── wap
│   │   │       ├── Wbxml.class
│   │   │       ├── WbxmlParser.class
│   │   │       ├── WbxmlSerializer.class
│   │   │       ├── syncml
│   │   │       │   └── SyncML.class
│   │   │       ├── wml
│   │   │       │   └── Wml.class
│   │   │       └── wv
│   │   │           └── WV.class
│   │   └── xmlpull
│   │       └── v1
│   │           ├── XmlPullParser.class
│   │           ├── XmlPullParserException.class
│   │           ├── XmlPullParserFactory.class
│   │           └── XmlSerializer.class
│   ├── spacesettlers
│   │   ├── actions
│   │   │   ├── AbstractAction$SpacewarActionCallable.class
│   │   │   ├── AbstractAction.class
│   │   │   ├── DoNothingAction.class
│   │   │   ├── MoveAction.class
│   │   │   ├── MoveToObjectAction.class
│   │   │   ├── PurchaseCosts.class
│   │   │   ├── PurchaseTypes.class
│   │   │   ├── RawAction.class
│   │   │   └── SpaceSettlersActionException.class
│   │   ├── clients
│   │   │   ├── AggressiveHeuristicAsteroidCollectorSingletonTeamClient.class
│   │   │   ├── AggressiveHeuristicAsteroidCollectorTeamClient.class
│   │   │   ├── BeaconCollectorTeamClient.class
│   │   │   ├── DoNothingTeamClient.class
│   │   │   ├── ExampleKnowledge.class
│   │   │   ├── HumanTeamClient$HumanClientKeyListener.class
│   │   │   ├── HumanTeamClient$HumanKeyPressed.class
│   │   │   ├── HumanTeamClient$HumanMouseListener.class
│   │   │   ├── HumanTeamClient.class
│   │   │   ├── ImmutableTeamInfo.class
│   │   │   ├── InfiniteLoopTeamClient.class
│   │   │   ├── PacifistHeuristicAsteroidCollectorTeamClient.class
│   │   │   ├── RandomTeamClient.class
│   │   │   ├── Team$1.class
│   │   │   ├── Team$2.class
│   │   │   ├── Team$3.class
│   │   │   ├── Team$4.class
│   │   │   ├── Team$5.class
│   │   │   ├── Team.class
│   │   │   └── TeamClient.class
│   │   ├── configs
│   │   │   ├── AsteroidConfig.class
│   │   │   ├── BaseConfig.class
│   │   │   ├── HighLevelTeamConfig.class
│   │   │   ├── LadderConfig.class
│   │   │   ├── SpaceSettlersConfig.class
│   │   │   ├── SpacewarConfig.class
│   │   │   └── TeamClientConfig.class
│   │   ├── graphics
│   │   │   ├── AsteroidGraphics.class
│   │   │   ├── BaseGraphics.class
│   │   │   ├── BeaconGraphics.class
│   │   │   ├── CircleGraphics.class
│   │   │   ├── EMPGraphics.class
│   │   │   ├── LineGraphics.class
│   │   │   ├── MissileGraphics.class
│   │   │   ├── PolygonGraphics.class
│   │   │   ├── RectangleGraphics.class
│   │   │   ├── ShipGraphics.class
│   │   │   ├── SpacewarGraphics.class
│   │   │   ├── StarGraphics.class
│   │   │   └── TargetGraphics.class
│   │   ├── gui
│   │   │   ├── GlobalInfoPanel.class
│   │   │   ├── JSpaceSettlersComponent.class
│   │   │   ├── JSpaceSettlersInfoPanel.class
│   │   │   ├── MyEmptyBorder.class
│   │   │   ├── ObjectInfoPanel$InnerObjectPanel.class
│   │   │   ├── ObjectInfoPanel.class
│   │   │   ├── ResourcesPanel.class
│   │   │   ├── SpaceSettlersGUI$1.class
│   │   │   ├── SpaceSettlersGUI$GUIMouseListener.class
│   │   │   ├── SpaceSettlersGUI$HelpMenuListener.class
│   │   │   ├── SpaceSettlersGUI.class
│   │   │   └── TeamInfoPanel.class
│   │   ├── ladder
│   │   │   ├── Ladder.class
│   │   │   ├── RunLadder.class
│   │   │   ├── TeamRecord.class
│   │   │   └── TeamRecordComparator.class
│   │   ├── objects
│   │   │   ├── AbstractActionableObject.class
│   │   │   ├── AbstractObject.class
│   │   │   ├── Asteroid.class
│   │   │   ├── Base.class
│   │   │   ├── Beacon.class
│   │   │   ├── Ship.class
│   │   │   ├── powerups
│   │   │   │   ├── PowerupDoubleHealingBaseEnergy.class
│   │   │   │   ├── PowerupDoubleMaxEnergy.class
│   │   │   │   ├── PowerupDoubleWeapon.class
│   │   │   │   ├── PowerupToggleShield.class
│   │   │   │   ├── SpaceSettlersPowerup.class
│   │   │   │   └── SpaceSettlersPowerupEnum.class
│   │   │   ├── resources
│   │   │   │   ├── ResourceFactory$1.class
│   │   │   │   ├── ResourceFactory.class
│   │   │   │   ├── ResourcePile.class
│   │   │   │   └── ResourceTypes.class
│   │   │   └── weapons
│   │   │       ├── AbstractWeapon.class
│   │   │       ├── EMP.class
│   │   │       └── Missile.class
│   │   ├── simulator
│   │   │   ├── CollisionHandler$CollisionData.class
│   │   │   ├── CollisionHandler.class
│   │   │   ├── RunSimulator.class
│   │   │   ├── SimulatorException.class
│   │   │   ├── SpaceSettlersSimulator$1.class
│   │   │   ├── SpaceSettlersSimulator$AdvanceTimeCallable.class
│   │   │   ├── SpaceSettlersSimulator.class
│   │   │   ├── Toroidal2DPhysics$1.class
│   │   │   └── Toroidal2DPhysics.class
│   │   └── utilities
│   │       ├── Movement.class
│   │       ├── Position.class
│   │       └── Vector2D.class
│   └── vanh4509
│       ├── project1.class
│       └── vanh4509.class
├── build.xml
├── config
│   ├── heuristicCompetitive
│   │   ├── LadderConfig.xml
│   │   ├── SpaceSettlersConfig.xml
│   │   ├── aggressive-heuristic-clientinit.xml
│   │   ├── beacon-clientinit.xml
│   │   ├── donothing-clientinit.xml
│   │   ├── human-clientinit.xml
│   │   ├── passive-heuristic-clientinit.xml
│   │   └── random-clientinit.xml
│   ├── heuristicCooperative
│   │   ├── LadderConfig.xml
│   │   ├── SpaceSettlersConfig.xml
│   │   ├── aggressive-heuristic-clientinit.xml
│   │   ├── beacon-clientinit.xml
│   │   ├── donothing-clientinit.xml
│   │   ├── human-clientinit.xml
│   │   ├── passive-heuristic-clientinit.xml
│   │   └── random-clientinit.xml
│   ├── human
│   │   ├── SpaceSettlersConfig.xml
│   │   ├── aggressive-heuristic-clientinit.xml
│   │   ├── beacon-clientinit.xml
│   │   ├── donothing-clientinit.xml
│   │   ├── human-clientinit.xml
│   │   ├── passive-heuristic-clientinit.xml
│   │   ├── random-clientinit.xml
│   │   └── spacesettlersinit.xml
│   └── ladder
│       ├── SelfLadderConfig.xml
│       ├── SpaceSettlersConfig.xml
│       ├── aggressive-heuristic-clientinit.xml
│       ├── beacon-clientinit.xml
│       ├── donothing-clientinit.xml
│       ├── passive-heuristic-clientinit.xml
│       ├── random-clientinit.xml
│       └── self-ladder.html
├── dist
│   └── spacesettlers.jar
├── docs
│   └── api
├── lib
│   ├── JSAP-2.1.jar
│   ├── kxml2-2-1.3.0.jar
│   └── xstream-1.4.2.jar
├── src
│   ├── spacesettlers
│   │   ├── actions
│   │   │   ├── AbstractAction$SpacewarActionCallable.class
│   │   │   ├── AbstractAction.class
│   │   │   ├── AbstractAction.java
│   │   │   ├── DoNothingAction.class
│   │   │   ├── DoNothingAction.java
│   │   │   ├── MoveAction.class
│   │   │   ├── MoveAction.java
│   │   │   ├── MoveToObjectAction.class
│   │   │   ├── MoveToObjectAction.java
│   │   │   ├── PurchaseCosts.class
│   │   │   ├── PurchaseCosts.java
│   │   │   ├── PurchaseTypes.class
│   │   │   ├── PurchaseTypes.java
│   │   │   ├── RawAction.class
│   │   │   ├── RawAction.java
│   │   │   ├── SpaceSettlersActionException.class
│   │   │   ├── SpaceSettlersActionException.java
│   │   │   └── package.html
│   │   ├── clients
│   │   │   ├── AggressiveHeuristicAsteroidCollectorSingletonTeamClient.java
│   │   │   ├── AggressiveHeuristicAsteroidCollectorTeamClient.class
│   │   │   ├── AggressiveHeuristicAsteroidCollectorTeamClient.java
│   │   │   ├── BeaconCollectorTeamClient.class
│   │   │   ├── BeaconCollectorTeamClient.java
│   │   │   ├── DoNothingTeamClient.class
│   │   │   ├── DoNothingTeamClient.java
│   │   │   ├── ExampleKnowledge.class
│   │   │   ├── ExampleKnowledge.java
│   │   │   ├── HumanTeamClient$HumanClientKeyListener.class
│   │   │   ├── HumanTeamClient$HumanKeyPressed.class
│   │   │   ├── HumanTeamClient$HumanMouseListener.class
│   │   │   ├── HumanTeamClient.class
│   │   │   ├── HumanTeamClient.java
│   │   │   ├── ImmutableTeamInfo.class
│   │   │   ├── ImmutableTeamInfo.java
│   │   │   ├── InfiniteLoopTeamClient.class
│   │   │   ├── InfiniteLoopTeamClient.java
│   │   │   ├── PacifistHeuristicAsteroidCollectorTeamClient.class
│   │   │   ├── PacifistHeuristicAsteroidCollectorTeamClient.java
│   │   │   ├── RandomTeamClient.class
│   │   │   ├── RandomTeamClient.java
│   │   │   ├── Team$1.class
│   │   │   ├── Team$2.class
│   │   │   ├── Team$3.class
│   │   │   ├── Team$4.class
│   │   │   ├── Team$5.class
│   │   │   ├── Team.class
│   │   │   ├── Team.java
│   │   │   ├── TeamClient.class
│   │   │   ├── TeamClient.java
│   │   │   ├── aggressive_knowledge.xml
│   │   │   └── package.html
│   │   ├── configs
│   │   │   ├── AsteroidConfig.class
│   │   │   ├── AsteroidConfig.java
│   │   │   ├── BaseConfig.class
│   │   │   ├── BaseConfig.java
│   │   │   ├── HighLevelTeamConfig.class
│   │   │   ├── HighLevelTeamConfig.java
│   │   │   ├── LadderConfig.class
│   │   │   ├── LadderConfig.java
│   │   │   ├── SpaceSettlersConfig.class
│   │   │   ├── SpaceSettlersConfig.java
│   │   │   ├── SpacewarConfig.class
│   │   │   ├── SpacewarConfig.java
│   │   │   ├── TeamClientConfig.class
│   │   │   ├── TeamClientConfig.java
│   │   │   └── package.html
│   │   ├── graphics
│   │   │   ├── AsteroidGraphics.class
│   │   │   ├── AsteroidGraphics.java
│   │   │   ├── BaseGraphics.class
│   │   │   ├── BaseGraphics.java
│   │   │   ├── BeaconGraphics.class
│   │   │   ├── BeaconGraphics.java
│   │   │   ├── CircleGraphics.class
│   │   │   ├── CircleGraphics.java
│   │   │   ├── EMPGraphics.class
│   │   │   ├── EMPGraphics.java
│   │   │   ├── LineGraphics.class
│   │   │   ├── LineGraphics.java
│   │   │   ├── MissileGraphics.class
│   │   │   ├── MissileGraphics.java
│   │   │   ├── PolygonGraphics.class
│   │   │   ├── PolygonGraphics.java
│   │   │   ├── RectangleGraphics.class
│   │   │   ├── RectangleGraphics.java
│   │   │   ├── ShipGraphics.class
│   │   │   ├── ShipGraphics.java
│   │   │   ├── SpacewarGraphics.class
│   │   │   ├── SpacewarGraphics.java
│   │   │   ├── StarGraphics.class
│   │   │   ├── StarGraphics.java
│   │   │   ├── TargetGraphics.class
│   │   │   ├── TargetGraphics.java
│   │   │   └── package.html
│   │   ├── gui
│   │   │   ├── GlobalInfoPanel.class
│   │   │   ├── GlobalInfoPanel.java
│   │   │   ├── JSpaceSettlersComponent.class
│   │   │   ├── JSpaceSettlersComponent.java
│   │   │   ├── JSpaceSettlersInfoPanel.class
│   │   │   ├── JSpaceSettlersInfoPanel.java
│   │   │   ├── MyEmptyBorder.class
│   │   │   ├── MyEmptyBorder.java
│   │   │   ├── ObjectInfoPanel$InnerObjectPanel.class
│   │   │   ├── ObjectInfoPanel.class
│   │   │   ├── ObjectInfoPanel.java
│   │   │   ├── ResourcesPanel.class
│   │   │   ├── ResourcesPanel.java
│   │   │   ├── SpaceSettlersGUI$1.class
│   │   │   ├── SpaceSettlersGUI$GUIMouseListener.class
│   │   │   ├── SpaceSettlersGUI$HelpMenuListener.class
│   │   │   ├── SpaceSettlersGUI.class
│   │   │   ├── SpaceSettlersGUI.java
│   │   │   ├── TeamInfoPanel.class
│   │   │   ├── TeamInfoPanel.java
│   │   │   └── package.html
│   │   ├── ladder
│   │   │   ├── Ladder.class
│   │   │   ├── Ladder.java
│   │   │   ├── RunLadder.class
│   │   │   ├── RunLadder.java
│   │   │   ├── TeamRecord.class
│   │   │   ├── TeamRecord.java
│   │   │   ├── TeamRecordComparator.class
│   │   │   ├── TeamRecordComparator.java
│   │   │   └── package.html
│   │   ├── objects
│   │   │   ├── AbstractActionableObject.class
│   │   │   ├── AbstractActionableObject.java
│   │   │   ├── AbstractObject.class
│   │   │   ├── AbstractObject.java
│   │   │   ├── Asteroid.class
│   │   │   ├── Asteroid.java
│   │   │   ├── Base.class
│   │   │   ├── Base.java
│   │   │   ├── Beacon.class
│   │   │   ├── Beacon.java
│   │   │   ├── Ship.class
│   │   │   ├── Ship.java
│   │   │   ├── package.html
│   │   │   ├── powerups
│   │   │   │   ├── PowerupDoubleHealingBaseEnergy.class
│   │   │   │   ├── PowerupDoubleHealingBaseEnergy.java
│   │   │   │   ├── PowerupDoubleMaxEnergy.class
│   │   │   │   ├── PowerupDoubleMaxEnergy.java
│   │   │   │   ├── PowerupDoubleWeapon.class
│   │   │   │   ├── PowerupDoubleWeapon.java
│   │   │   │   ├── PowerupToggleShield.class
│   │   │   │   ├── PowerupToggleShield.java
│   │   │   │   ├── SpaceSettlersPowerup.class
│   │   │   │   ├── SpaceSettlersPowerup.java
│   │   │   │   ├── SpaceSettlersPowerupEnum.class
│   │   │   │   ├── SpaceSettlersPowerupEnum.java
│   │   │   │   └── package.html
│   │   │   ├── resources
│   │   │   │   ├── ResourceFactory.class
│   │   │   │   ├── ResourceFactory.java
│   │   │   │   ├── ResourcePile.class
│   │   │   │   ├── ResourcePile.java
│   │   │   │   ├── ResourceTypes.class
│   │   │   │   ├── ResourceTypes.java
│   │   │   │   └── package.html
│   │   │   └── weapons
│   │   │       ├── AbstractWeapon.class
│   │   │       ├── AbstractWeapon.java
│   │   │       ├── EMP.class
│   │   │       ├── EMP.java
│   │   │       ├── Missile.class
│   │   │       ├── Missile.java
│   │   │       └── package.html
│   │   ├── overview.html
│   │   ├── simulator
│   │   │   ├── CollisionHandler$CollisionData.class
│   │   │   ├── CollisionHandler.class
│   │   │   ├── CollisionHandler.java
│   │   │   ├── RunSimulator.class
│   │   │   ├── RunSimulator.java
│   │   │   ├── SimulatorException.class
│   │   │   ├── SimulatorException.java
│   │   │   ├── SpaceSettlersSimulator$AdvanceTimeCallable.class
│   │   │   ├── SpaceSettlersSimulator.class
│   │   │   ├── SpaceSettlersSimulator.java
│   │   │   ├── Toroidal2DPhysics.class
│   │   │   ├── Toroidal2DPhysics.java
│   │   │   └── package.html
│   │   └── utilities
│   │       ├── Movement.class
│   │       ├── Movement.java
│   │       ├── Position.class
│   │       ├── Position.java
│   │       ├── Vector2D.class
│   │       ├── Vector2D.java
│   │       └── package.html
│   └── vanh4509
│       ├── BiasedBeacon.java
│       ├── BiasedShip.java
│       ├── project1.java
│       ├── vanh4509.class
│       └── vanh4509.java
└── test
    ├── RunAllTests.java
    └── spacesettlers
        ├── actions
        │   ├── ActionTests.java
        │   └── TestMoveAction.java
        ├── ladder
        │   └── TestLadder.java
        ├── simulator
        │   ├── SimulatorTests.java
        │   ├── TestCollisionHandler.java
        │   └── TestToroidal2DPhysics.java
        └── utilities
            ├── TestVector2D.java
            └── UtilitiesTest.java

90 directories, 824 files

___


## Part 1 : The High Level  

To begin, we separated the goals of our agressive agent into a series of distinct lobes of reasoning.  These included the following three categories: 

1. *Attack Weight* - How much do we want to attack another ship? 
2. *Energy Weight* - How much do we want to go get energy?
3. *Resource Weight* - How much do we want to go mine resources?

## Part 2 : The Biases


#### Energy

_Parameters_  

<table>
	<th>
		Type
	</th>
	<th>
		Name
	</th>
	<th>
		Description
	</th>
	<tr>
		<td>
			Toroidal2DPhysics
		</td>  
		<td>
			space
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
	<tr>
		<td>
			Ship
		</td>  
		<td>
			ship
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
</table>

_Method_

~~~java
private double getEnergyActionBias(Toroidal2DPhysics space, Ship ship ){ ... } 
~~~

_Implementation_

From the space, we will iterate over all the available `Beacon` objects to find each ranked in terms of its distance and amount of energy onboard.  

Then, of these, we will divide the energy onboard by the distance to give us an overall bias of how valuable this particular `Beacon` is to us, multiply by a scaling factor, and add a constant term (both the multiplying factor and additive term will be adjustable for future testing) 

In particular, lets take a `Beacon` _b_ and see what its weight would be computed as.  




--  

#### Attack  

_Parameters_

<table>
	<th>
		Type
	</th>
	<th>
		Name
	</th>
	<th>
		Description
	</th>
	<tr>
		<td>
			Toroidal2DPhysics
		</td>  
		<td>
			space
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
	<tr>
		<td>
			Ship
		</td>  
		<td>
			ship
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
</table>

_Method_

~~~ java
private double getAttackActionBias(Toroidal2DPhysics space, Ship ship ){ ... }
~~~

_Implementation_


--

#### Resources

_Parameters_

<table>
	<th>
		Type
	</th>
	<th>
		Name
	</th>
	<th>
		Description
	</th>
	<tr>
		<td>
			Toroidal2DPhysics
		</td>  
		<td>
			space
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
	<tr>
		<td>
			Ship
		</td>  
		<td>
			ship
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
</table>

_Method_

~~~ java
private double getResourcesActionBias(Toroidal2DPhysics space, Ship ship ){ ... } 
~~~

_Implementation_


--

## Part 3 : The Actions
